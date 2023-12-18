package com.aarontan.DailyUpdates.service.impl;

import com.aarontan.DailyUpdates.exceptions.AccessDeniedException;
import com.aarontan.DailyUpdates.exceptions.DoesNotExistException;
import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.repository.FeedRepository;
import com.aarontan.DailyUpdates.security.UserDetailsServiceImpl;
import com.aarontan.DailyUpdates.service.FeedService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public FeedServiceImpl(FeedRepository feedRepository, UserDetailsServiceImpl userDetailsService) {
        this.feedRepository = feedRepository;
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
}
