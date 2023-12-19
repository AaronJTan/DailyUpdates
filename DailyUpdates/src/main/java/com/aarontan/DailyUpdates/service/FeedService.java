package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.request.FeedSourceRequest;
import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.aarontan.DailyUpdates.repository.FeedRepository;

import java.util.List;

public interface FeedService {
    Feed createFeed(FeedRequest feedRequest, long userid);
    List<FeedRepository.FeedsOnly> getUserFeeds(long userid);
    Feed getFeedSources(long userId, int feedId);
    Feed updateFeed(FeedRequest feedRequest, long userid, int feedId);
    void deleteFeed(long userid, int feedId);
}