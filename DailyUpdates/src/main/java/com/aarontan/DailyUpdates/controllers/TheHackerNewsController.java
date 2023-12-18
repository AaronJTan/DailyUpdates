package com.aarontan.DailyUpdates.controllers;

import java.io.IOException;
import java.util.List;

import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarontan.DailyUpdates.service.TheHackerNewsService;

@RestController
@RequestMapping(path = "/thehackernews")
public class TheHackerNewsController {
    private TheHackerNewsService theHackerNewsService;

    @Autowired
    public TheHackerNewsController(TheHackerNewsService theHackerNewsService) {
        this.theHackerNewsService = theHackerNewsService;
    }

    @GetMapping("/latest-news")
    public ResponseEntity<ApiResponse> getLatestNews() throws IOException {
        List<NewsArticleDetails> latestNews = theHackerNewsService.getLatestNews();

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(latestNews)
                .build();
    }
}
