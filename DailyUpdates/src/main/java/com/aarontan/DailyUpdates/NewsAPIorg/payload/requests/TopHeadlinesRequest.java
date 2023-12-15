package com.aarontan.DailyUpdates.NewsAPIorg.payload.requests;

import lombok.Builder;

@Builder
public class TopHeadlinesRequest {
    private String country;
    private String category;
    private String sources;
    private String q;
    private String pageSize;
    private String page;
}
