package com.aarontan.DailyUpdates.controllers;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/")
public class FeedController {
    private final FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @PostMapping("/users/{userid}/feeds")
    public ResponseEntity<ApiResponse> createFeed(@PathVariable("userid") long userId, @RequestBody FeedRequest feedRequest) {
        Feed feed = feedService.createFeed(feedRequest, userId);

        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.CREATED)
				.setData(feed)
				.build();
    }

    @PostMapping("/users/{userId}/feeds/{feedId}")
    public ResponseEntity<ApiResponse> updateFeed(@PathVariable("userId") long userId, @PathVariable("feedId") int feedId, @RequestBody FeedRequest feedRequest) {
        Feed feed = feedService.updateFeed(feedRequest, userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feed)
                .build();
    }


    @DeleteMapping("/users/{userId}/feeds/{feedId}")
    public ResponseEntity<ApiResponse> deleteFeed(@PathVariable("userId") long userId, @PathVariable("feedId") int feedId) {
        feedService.deleteFeed(userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.NO_CONTENT)
                .build();
    }

}
