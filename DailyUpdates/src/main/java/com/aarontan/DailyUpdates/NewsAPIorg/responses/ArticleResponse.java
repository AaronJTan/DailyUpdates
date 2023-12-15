package com.aarontan.DailyUpdates.NewsAPIorg.responses;

import com.aarontan.DailyUpdates.NewsAPIorg.models.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;
}
