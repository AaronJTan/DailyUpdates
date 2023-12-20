package com.aarontan.DailyUpdates.service.impl;

import com.aarontan.DailyUpdates.exceptions.DoesNotExistException;
import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.models.TopSource;
import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.request.FeedSourceRequest;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.repository.FeedRepository;
import com.aarontan.DailyUpdates.repository.SourceRepository;
import com.aarontan.DailyUpdates.security.UserDetailsServiceImpl;
import com.aarontan.DailyUpdates.service.FeedService;
import com.aarontan.DailyUpdates.service.NewsAPIService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public Feed createFeed(FeedRequest feedRequest, long userId) {
        try {
            User user = (User) userDetailsService.loadUserById(userId);
            return feedRepository.save(new Feed(feedRequest.getName(), user));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(String.format("Feed with name: `%s` already exists.", feedRequest.getName()));
        }
    }

    @Override
    public Feed addSourceToFeed(FeedSourceRequest feedSourceRequest, long userId, int feedId) {
        try {
            Feed feed = getFeedByIdAndVerifyOwnership(feedId, userId);
            TopSource source = getSourceById(feedSourceRequest.getSourceId());

            feed.getSources().add(source);
            return feedRepository.save(feed);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(String.format("Source with id: `%s` already exists in feed.", feedSourceRequest.getSourceId()));
        }
    }

    @Override
    public List<FeedRepository.FeedsOnly> getUserFeeds(long userId) {
        User user = (User) userDetailsService.loadUserById(userId);
        return feedRepository.findByUserOrderByIdDesc(user);
    }

    @Override
    public Feed getFeedSources(long userId, int feedId) {
        return getFeedByIdAndVerifyOwnership(feedId, userId);
    }

    @Override
    public ArticleResponse getFeedArticles(long userId, int feedId, Map<String, String> queryParams) {
        getFeedByIdAndVerifyOwnership(feedId, userId);
        String feedSources = feedRepository.findSourceIdsByFeedId(feedId);

        Set<String> keysToRetain = Set.of("sortBy", "pageSize", "page");
        queryParams.keySet().retainAll(keysToRetain);
        queryParams.put("sources", feedSources);

        return newsAPIService.getEverything(queryParams);
    }

    @Override
    public Feed updateFeed(FeedRequest feedRequest, long userId, int feedId) {
        Feed feed = getFeedByIdAndVerifyOwnership(feedId, userId);
        feed.setName(feedRequest.getName());

        try {
            return feedRepository.save(feed);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Feed with name: `" + feedRequest.getName() + "` already exists.");
        }
    }

    @Override
    public void deleteFeed(long userId, int feedId) {
        Feed feed = getFeedByIdAndVerifyOwnership(feedId, userId);
        feedRepository.delete(feed);
    }

    @Override
    public Feed deleteSourceFromFeed(FeedSourceRequest feedSourceRequest, long userId, int feedId) {
        Feed feed = getFeedByIdAndVerifyOwnership(feedId, userId);
        String sourceId = feedSourceRequest.getSourceId();
        TopSource source = getSourceById(sourceId);

        if (!feed.getSources().remove(source)) {
            throw new DoesNotExistException(String.format("Source with id: `%s` does not exist in feed.", sourceId));
        }

        return feedRepository.save(feed);
    }

    private Feed getFeedByIdAndVerifyOwnership(int feedId, long userId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new DoesNotExistException(String.format("Feed with id: `%d` does not exist.", feedId)));

        verifyOwnership(feed, userId);

        return feed;
    }

    private void verifyOwnership(Feed feed, long userId) {
        if (feed.getUserId() != userId) {
            throw new AccessDeniedException("You are not authorized to access this feed.");
        }
    }

    private TopSource getSourceById(String sourceId) {
        return sourceRepository.findById(sourceId)
                .orElseThrow(() -> new DoesNotExistException(String.format("Source with id: `%s` does not exist.", sourceId)));
    }
}
