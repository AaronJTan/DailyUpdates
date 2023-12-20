package com.aarontan.DailyUpdates.controllers;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.payload.request.FeedRequest;
import com.aarontan.DailyUpdates.payload.request.FeedSourceRequest;
import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.repository.FeedRepository;
import com.aarontan.DailyUpdates.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("#userId == principal.id")
@RestController
public class FeedController {
    private final FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @PostMapping("/users/{userId}/feeds")
    public ResponseEntity<ApiResponse> createFeed(@PathVariable("userId") long userId, @Valid @RequestBody FeedRequest feedRequest) {
        Feed feed = feedService.createFeed(feedRequest, userId);

        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.CREATED)
				.setData(feed)
				.build();
    }

    @PostMapping("/users/{userId}/feeds/{feedId}/sources")
    public ResponseEntity<ApiResponse> addSourceToFeed(@PathVariable("userId") long userId,
                                                       @PathVariable("feedId") int feedId,
                                                       @Valid @RequestBody FeedSourceRequest feedSourceRequest) {
        Feed feed = feedService.addSourceToFeed(feedSourceRequest, userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.CREATED)
                .setData(feed)
                .build();
    }

    @GetMapping("/users/{userId}/feeds")
    public ResponseEntity<ApiResponse> getFeeds(@PathVariable("userId") long userId) {
        List<FeedRepository.FeedsOnly> feeds = feedService.getUserFeeds(userId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feeds)
                .build();
    }

    @GetMapping("/users/{userId}/feeds/{feedId}/sources")
    public ResponseEntity<ApiResponse> getFeedSources(@PathVariable("userId") long userId, @PathVariable("feedId") int feedId) {
        Feed feed = feedService.getFeedSources(userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feed)
                .build();
    }

    @GetMapping("/users/{userId}/feeds/{feedId}/articles")
    public ResponseEntity<ApiResponse> getFeeds(@PathVariable("userId") long userId, @PathVariable("feedId") int feedId) {
        ArticleResponse feeds = feedService.getFeedArticles(userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feeds)
                .build();
    }

    @PutMapping("/users/{userId}/feeds/{feedId}")
    public ResponseEntity<ApiResponse> updateFeed(@PathVariable("userId") long userId, @PathVariable("feedId") int feedId, @Valid @RequestBody FeedRequest feedRequest) {
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

    @DeleteMapping("/users/{userId}/feeds/{feedId}/sources")
    public ResponseEntity<ApiResponse> deleteSourceFromFeed(@PathVariable("userId") long userId,
               @PathVariable("feedId") int feedId,
               @Valid @RequestBody FeedSourceRequest feedSourceRequest) {
        Feed feed = feedService.deleteSourceFromFeed(feedSourceRequest, userId, feedId);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(feed)
                .build();
    }

}
