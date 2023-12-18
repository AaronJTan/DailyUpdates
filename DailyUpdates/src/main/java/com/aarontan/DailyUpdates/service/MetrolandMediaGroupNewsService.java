package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;

import java.util.List;

public interface MetrolandMediaGroupNewsService {
    List<NewsArticleDetails> getLatestNews(String url);
}
