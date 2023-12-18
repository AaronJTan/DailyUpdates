package com.aarontan.DailyUpdates.controllers;

import java.io.IOException;
import java.util.List;

import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarontan.DailyUpdates.pojos.news.cp24.Article;
import com.aarontan.DailyUpdates.service.CP24NewsService;

@RestController
@RequestMapping(path = "/cp24")
public class CP24Controller {
    private CP24NewsService cp24NewsService;

    @Autowired
    public CP24Controller(CP24NewsService cp24NewsService) {
        this.cp24NewsService = cp24NewsService;
    }

    @GetMapping("/latest-news")
    public ResponseEntity<ApiResponse> getLatestNews() throws IOException {
        List<Article> latestNews = cp24NewsService.getLatestNews();

        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.OK)
				.setData(latestNews)
				.build();

    }

    @GetMapping("/world-news")
    public ResponseEntity<ApiResponse> getWorldNews() throws IOException {
        List<Article> worldNews = cp24NewsService.getWorldNews();

        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.OK)
				.setData(worldNews)
				.build();

    }
}
