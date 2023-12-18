package com.aarontan.DailyUpdates.service;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.news.cp24.Article;

public interface CP24NewsService {
    public List<Article> getLatestNews();
    public List<Article> getWorldNews();
}
