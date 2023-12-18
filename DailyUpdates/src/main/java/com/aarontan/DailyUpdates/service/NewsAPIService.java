package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.SourceResponse;

import java.util.Map;

public interface NewsAPIService {
    SourceResponse getTopSources(Map<String, String> queryParams);
    ArticleResponse getTopHeadlines(Map<String, String> queryParams);
    ArticleResponse getEverything(Map<String, String> queryParams);
}
