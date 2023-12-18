package com.aarontan.DailyUpdates.payload.request.NewsAPIorg;

import lombok.Builder;

@Builder
public class EverythingRequest {
    private String q;
    private String searchIn;
    private String sources;
    private String domains;
    private String excludeDomains;
    private String from;
    private String to;
    private String language;
    private String sortBy;
    private String pageSize;
    private String page;
}
