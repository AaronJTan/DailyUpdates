package com.aarontan.DailyUpdates.service.impl;

import com.aarontan.DailyUpdates.exceptions.AccessDeniedException;
import com.aarontan.DailyUpdates.exceptions.DoesNotExistException;
import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.models.TopSource;
import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.request.FeedSourceRequest;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.aarontan.DailyUpdates.repository.FeedRepository;
import com.aarontan.DailyUpdates.repository.SourceRepository;
import com.aarontan.DailyUpdates.security.UserDetailsServiceImpl;
import com.aarontan.DailyUpdates.service.FeedService;
import com.aarontan.DailyUpdates.service.NewsAPIService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final SourceRepository sourceRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final NewsAPIService newsAPIService;

    public FeedServiceImpl(FeedRepository feedRepository, SourceRepository sourceRepository,
                           UserDetailsServiceImpl userDetailsService, NewsAPIService newsAPIService) {
        this.feedRepository = feedRepository;
        this.sourceRepository = sourceRepository;
        this.userDetailsService = userDetailsService;
        this.newsAPIService = newsAPIService;
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
    public Feed addSourceToFeed(FeedSourceRequest feedSourceRequest, long userId, int feedId) {
        try {
            Feed feed = feedRepository.findById(feedId)
                    .orElseThrow(() -> new DoesNotExistException("Feed with id: " + feedId + " does not exist."));

            if (feed.getUserId() != userId) {
                throw new AccessDeniedException("You are not authorized to edit this feed.");
            }

            TopSource source = sourceRepository.findById(feedSourceRequest.getSourceId())
                    .orElseThrow(() -> new DoesNotExistException("Source with id: " + feedSourceRequest.getSourceId() + " does not exist."));

            feed.getSources().add(source);
            return feedRepository.save(feed);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Source with id: `" + feedSourceRequest.getSourceId() + "` already exists in feed.");
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
    public ArticleResponse getFeedArticles(long userId, int feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new DoesNotExistException("Feed with id: " + feedId + " does not exist."));

        if (feed.getUserId() != userId) {
            throw new AccessDeniedException("You are not authorized to edit this feed.");
        }

        String feedSources = feedRepository.findSourceIdsByFeedId(feedId);
        Map<String, String> params = new HashMap<>();
        params.put("sources", feedSources);
        return newsAPIService.getEverything(params);
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

    @Override
    public Feed deleteSourceFromFeed(FeedSourceRequest feedSourceRequest, long userId, int feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new DoesNotExistException("Feed with id: " + feedId + " does not exist."));

        if (feed.getUserId() != userId) {
            throw new AccessDeniedException("You are not authorized to edit this feed.");
        }

        TopSource source = sourceRepository.findById(feedSourceRequest.getSourceId())
                .orElseThrow(() -> new DoesNotExistException("Source with id: " + feedSourceRequest.getSourceId() + " does not exist."));

        feed.getSources().remove(source);
        return feedRepository.save(feed);
    }
}
