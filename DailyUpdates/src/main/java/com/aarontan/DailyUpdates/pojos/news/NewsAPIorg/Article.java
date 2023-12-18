package com.aarontan.DailyUpdates.pojos.news.NewsAPIorg;

import lombok.Data;

@Data
public class Article {
    private ArticleSource source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}

@Data
class ArticleSource {
    private String id;
    private String name;
}