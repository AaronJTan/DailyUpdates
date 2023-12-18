package com.aarontan.DailyUpdates.payload.request.NewsAPIorg;

import lombok.Builder;

@Builder
public class SourcesRequest {
    private String category;
    private String language;
    private String country;
}
