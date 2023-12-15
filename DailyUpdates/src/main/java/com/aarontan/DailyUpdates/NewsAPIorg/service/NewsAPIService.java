package com.aarontan.DailyUpdates.NewsAPIorg.service;

import com.aarontan.DailyUpdates.NewsAPIorg.payload.responses.ArticleResponse;
import com.aarontan.DailyUpdates.NewsAPIorg.payload.responses.SourceResponse;

import java.util.Map;

public interface NewsAPIService {
    SourceResponse getTopSources(Map<String, String> queryParams);
    ArticleResponse getTopHeadlines(Map<String, String> queryParams);
}
