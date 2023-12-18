package com.aarontan.DailyUpdates.service;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;

public interface CP24NewsService {
    public List<NewsArticleDetails> getLatestNews();
    public List<NewsArticleDetails> getWorldNews();
}
