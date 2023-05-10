package com.aarontan.DailyUpdates.News.TheHackerNews.pojos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article {
    private String headline;
    private String url;
    private String tags;
    private String dateTime;
}
