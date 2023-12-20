package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.request.FeedSourceRequest;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.aarontan.DailyUpdates.repository.FeedRepository;

import java.util.List;
import java.util.Map;

public interface FeedService {
    Feed createFeed(FeedRequest feedRequest, long userid);
    Feed addSourceToFeed(FeedSourceRequest feedSourceRequest, long userId, int feedId);
    List<FeedRepository.FeedsOnly> getUserFeeds(long userid);
    Feed getFeedSources(long userId, int feedId);
    ArticleResponse getFeedArticles(long userId, int feedId, Map<String, String> queryParams);
    Feed updateFeed(FeedRequest feedRequest, long userid, int feedId);
    void deleteFeed(long userid, int feedId);
    Feed deleteSourceFromFeed(FeedSourceRequest feedSourceRequest, long userId, int feedId);
}
