package com.aarontan.DailyUpdates.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarontan.DailyUpdates.pojos.news.TheHackerNews.Article;
import com.aarontan.DailyUpdates.service.TheHackerNewsService;
import com.aarontan.DailyUpdates.response.ResponseObj;

@RestController
@RequestMapping(path = "/thehackernews")
public class TheHackerNewsController {
    private TheHackerNewsService theHackerNewsService;

    @Autowired
    public TheHackerNewsController(TheHackerNewsService theHackerNewsService) {
        this.theHackerNewsService = theHackerNewsService;
    }

    @GetMapping("/latest-news")
    public ResponseEntity<ResponseObj> getLatestNews() throws IOException {
        List<Article> latestNews = theHackerNewsService.getLatestNews();

        return new ResponseObj.Builder()
				.setStatus(HttpStatus.OK)
				.setData(latestNews)
				.build();
    }
}
