package com.aarontan.DailyUpdates.NewsAPIorg.controllers;

import com.aarontan.DailyUpdates.NewsAPIorg.payload.responses.ArticleResponse;
import com.aarontan.DailyUpdates.NewsAPIorg.payload.responses.SourceResponse;
import com.aarontan.DailyUpdates.NewsAPIorg.service.NewsAPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NewsAPIController {
    private final NewsAPIService newsAPIService;

    public NewsAPIController(NewsAPIService newsAPIService) {
        this.newsAPIService = newsAPIService;
    }

    @GetMapping("/newsapi/top-headlines/sources")
    public SourceResponse getTopSources(@RequestParam Map<String, String> params) {
        return newsAPIService.getTopSources(params);
    }
}
