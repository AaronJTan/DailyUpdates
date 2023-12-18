package com.aarontan.DailyUpdates.controllers;

import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.SourceResponse;
import com.aarontan.DailyUpdates.service.NewsAPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/newsapi")
public class NewsAPIController {
    private final NewsAPIService newsAPIService;

    public NewsAPIController(NewsAPIService newsAPIService) {
        this.newsAPIService = newsAPIService;
    }

    @GetMapping("/top-headlines/sources")
    public SourceResponse getTopSources(@RequestParam Map<String, String> params) {
        return newsAPIService.getTopSources(params);
    }

    @GetMapping("/top-headlines")
    public ArticleResponse getTopHeadlines(@RequestParam Map<String, String> params) {
        return newsAPIService.getTopHeadlines(params);
    }

    @GetMapping("/everything")
    public ArticleResponse getEverything(@RequestParam Map<String, String> params) {
        return newsAPIService.getEverything(params);
    }
}