package com.aarontan.DailyUpdates.service;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;

public interface TheHackerNewsService {
    public List<NewsArticleDetails> getLatestNews();
}
