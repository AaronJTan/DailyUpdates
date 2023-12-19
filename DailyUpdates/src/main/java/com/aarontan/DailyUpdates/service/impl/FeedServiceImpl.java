package com.aarontan.DailyUpdates.service.impl;

import com.aarontan.DailyUpdates.exceptions.AccessDeniedException;
import com.aarontan.DailyUpdates.exceptions.DoesNotExistException;
import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.models.TopSource;
import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.request.FeedSourceRequest;
import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.aarontan.DailyUpdates.repository.FeedRepository;
import com.aarontan.DailyUpdates.repository.SourceRepository;
import com.aarontan.DailyUpdates.security.UserDetailsServiceImpl;
import com.aarontan.DailyUpdates.service.FeedService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final SourceRepository sourceRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public FeedServiceImpl(FeedRepository feedRepository, SourceRepository sourceRepository, UserDetailsServiceImpl userDetailsService) {
        this.feedRepository = feedRepository;
        this.sourceRepository = sourceRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Feed createFeed(FeedRequest feedRequest, long userid) {
        try {
            User user = (User) userDetailsService.loadUserById(userid);
            return feedRepository.save(new Feed(feedRequest.getName(), user));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Feed with name: `" + feedRequest.getName() + "` already exists.");
        }
    }

    @Override
    public List<FeedRepository.FeedsOnly> getUserFeeds(long userId) {
        User user = (User) userDetailsService.loadUserById(userId);
        return feedRepository.findByUserOrderByIdDesc(user);
    }

    @Override
    public Feed getFeedSources(long userId, int feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new DoesNotExistException("Feed with id: " + feedId + " does not exist."));

        if (feed.getUserId() != userId) {
            throw new AccessDeniedException("You are not authorized to edit this feed.");
        }

        return feed;
    }

    @Override
    public Feed updateFeed(FeedRequest feedRequest, long userid, int feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new DoesNotExistException("Feed with id: " + feedId + " does not exist."));

        if (feed.getUserId() != userid) {
            throw new AccessDeniedException("You are not authorized to edit this feed.");
        }

        feed.setName(feedRequest.getName());

        try {
            return feedRepository.save(feed);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Feed with name: `" + feedRequest.getName() + "` already exists.");
        }
    }

    @Override
    public void deleteFeed(long userid, int feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new DoesNotExistException("Feed with id: " + feedId + " does not exist."));

        if (feed.getUserId() != userid) {
            throw new AccessDeniedException("You are not authorized to delete this feed.");
        }

        feedRepository.delete(feed);
    }
}
