package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.exceptions.ConnectException;
import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ArticleWebScraper {
    protected final List<NewsArticleDetails> getArticles(String pageUrl) {
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements newsArticles = doc.select(getNewsArticlesCSSSelectors());

            return newsArticles.stream()
                    .map(this::mapElementToArticle)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new ConnectException(getConnectExceptionMessage());
        }
    }

    protected abstract String getNewsArticlesCSSSelectors();
    protected abstract NewsArticleDetails mapElementToArticle(Element article);
    protected abstract String getConnectExceptionMessage();
}
