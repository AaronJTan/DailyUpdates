package com.aarontan.DailyUpdates.News.CP24.services;

import java.io.IOException;
import java.util.List;

import com.aarontan.DailyUpdates.News.CP24.pojos.Article;

public interface CP24NewsService {
    public List<Article> getLatestNews() throws IOException;
    public List<Article> getWorldNews() throws IOException;
}
