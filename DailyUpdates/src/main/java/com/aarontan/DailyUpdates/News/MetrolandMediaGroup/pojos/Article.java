package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Article {
    private String label;
    private String headline;
    private String datetime;
    private String link;
}
