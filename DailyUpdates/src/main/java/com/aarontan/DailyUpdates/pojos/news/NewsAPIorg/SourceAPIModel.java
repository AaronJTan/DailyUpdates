package com.aarontan.DailyUpdates.pojos.news.NewsAPIorg;

import lombok.Data;

@Data
public class SourceAPIModel {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
}