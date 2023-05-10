package com.aarontan.DailyUpdates.News.TheHackerNews.services;

import java.io.IOException;
import java.util.List;

import com.aarontan.DailyUpdates.News.TheHackerNews.pojos.Article;

public interface TheHackerNewsService {
    public List<Article> getLatestNews() throws IOException;
}
