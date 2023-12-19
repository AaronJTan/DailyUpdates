package com.aarontan.DailyUpdates.controllers;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.request.FeedSourceRequest;
import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.aarontan.DailyUpdates.repository.FeedRepository;
import com.aarontan.DailyUpdates.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/users/{userid}/feeds/{feedId}/sources")
    public ResponseEntity<ApiResponse> addSourceToFeed(@PathVariable("userid") long userId,
                                                       @PathVariable("feedId") int feedId,
                                                       @RequestBody FeedSourceRequest feedSourceRequest) {
        Feed feed = feedService.addSourceToFeed(feedSourceRequest, userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.CREATED)
                .setData(feed)
                .build();
    }

    @GetMapping("/users/{userid}/feeds")
    public ResponseEntity<ApiResponse> getFeeds(@PathVariable("userid") long userId) {
        List<FeedRepository.FeedsOnly> feeds = feedService.getUserFeeds(userId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feeds)
                .build();
    }

    @GetMapping("/users/{userid}/feeds/{feedId}/sources")
    public ResponseEntity<ApiResponse> getFeedSources(@PathVariable("userid") long userId, @PathVariable("feedId") int feedId) {
        Feed feed = feedService.getFeedSources(userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feed)
                .build();
    }

    @GetMapping("/users/{userid}/feeds/{feedId}/articles")
    public ResponseEntity<ApiResponse> getFeeds(@PathVariable("userid") long userId, @PathVariable("feedId") int feedId) {
        ArticleResponse feeds = feedService.getFeedArticles(userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feeds)
                .build();
    }

    @PutMapping("/users/{userId}/feeds/{feedId}")
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

    @DeleteMapping("/users/{userid}/feeds/{feedId}/sources")
    public ResponseEntity<ApiResponse> deleteSourceFromFeed(@PathVariable("userid") long userId,
               @PathVariable("feedId") int feedId,
               @RequestBody FeedSourceRequest feedSourceRequest) {
        Feed feed = feedService.deleteSourceFromFeed(feedSourceRequest, userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feed)
                .build();
    }

}
