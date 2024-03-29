package com.aarontan.DailyUpdates.News.CP24.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarontan.DailyUpdates.News.CP24.pojos.Article;
import com.aarontan.DailyUpdates.News.CP24.services.CP24NewsService;
import com.aarontan.DailyUpdates.response.ResponseObj;

@RestController
@RequestMapping(path = "/cp24")
public class CP24Controller {
    private CP24NewsService cp24NewsService;

    @Autowired
    public CP24Controller(CP24NewsService cp24NewsService) {
        this.cp24NewsService = cp24NewsService;
    }

    @GetMapping("/latest-news")
    public ResponseEntity<ResponseObj> getLatestNews() throws IOException {
        List<Article> latestNews = cp24NewsService.getLatestNews();

        return new ResponseObj.Builder()
				.setStatus(HttpStatus.OK)
				.setData(latestNews)
				.build();

    }

    @GetMapping("/world-news")
    public ResponseEntity<ResponseObj> getWorldNews() throws IOException {
        List<Article> worldNews = cp24NewsService.getWorldNews();

        return new ResponseObj.Builder()
				.setStatus(HttpStatus.OK)
				.setData(worldNews)
				.build();

    }
}
