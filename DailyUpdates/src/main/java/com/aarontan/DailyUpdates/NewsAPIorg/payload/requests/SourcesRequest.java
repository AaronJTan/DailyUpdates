package com.aarontan.DailyUpdates.NewsAPIorg.payload.requests;

import lombok.Builder;

@Builder
public class SourcesRequest {
    private String category;
    private String language;
    private String country;
}
