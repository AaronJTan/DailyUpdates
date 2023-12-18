package com.aarontan.DailyUpdates.service;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.news.TheHackerNews.Article;

public interface TheHackerNewsService {
    public List<Article> getLatestNews();
}
