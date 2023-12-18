package com.aarontan.DailyUpdates.payload.response.NewsAPIorg;

import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;
}
