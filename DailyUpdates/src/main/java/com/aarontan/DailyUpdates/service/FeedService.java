package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;

public interface FeedService {
    Feed createFeed(FeedRequest feedRequest, long userid);
    Feed updateFeed(FeedRequest feedRequest, long userid, int feedId);
}
