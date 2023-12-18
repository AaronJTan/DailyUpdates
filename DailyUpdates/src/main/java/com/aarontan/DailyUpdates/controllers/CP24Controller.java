package com.aarontan.DailyUpdates.controllers;

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

import com.aarontan.DailyUpdates.service.CP24NewsService;

@RestController
@RequestMapping(path = "/cp24")
public class CP24Controller {
    private final CP24NewsService cp24NewsService;

    @Autowired
    public CP24Controller(CP24NewsService cp24NewsService) {
        this.cp24NewsService = cp24NewsService;
    }

    @GetMapping("/latest-news")
    public ResponseEntity<ApiResponse> getLatestNews() {
        List<NewsArticleDetails> latestNews = cp24NewsService.getLatestNews();

        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.OK)
				.setData(latestNews)
				.build();

    }

    @GetMapping("/world-news")
    public ResponseEntity<ApiResponse> getWorldNews() {
        List<NewsArticleDetails> worldNews = cp24NewsService.getWorldNews();

        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.OK)
				.setData(worldNews)
				.build();

    }
}
