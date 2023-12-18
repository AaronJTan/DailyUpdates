package com.aarontan.DailyUpdates.pojos.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class NewsArticleDetails {
    private String headline;
    private String url;
    private String tags;
    private String datetime;
}
