package com.aarontan.DailyUpdates.News.TheHackerNews.exceptions;

public class HackerNewsException extends RuntimeException {
    public HackerNewsException(String errorMessage) {
        super(errorMessage);
    }
}
