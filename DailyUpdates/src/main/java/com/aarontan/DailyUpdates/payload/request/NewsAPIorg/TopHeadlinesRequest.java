package com.aarontan.DailyUpdates.payload.request.NewsAPIorg;

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
