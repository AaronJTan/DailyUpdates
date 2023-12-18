package com.aarontan.DailyUpdates.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.aarontan.DailyUpdates.exceptions.ConnectException;
import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import com.aarontan.DailyUpdates.service.MetrolandMediaGroupNewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MetrolandMediaGroupNewsServiceImpl implements MetrolandMediaGroupNewsService {
    @Override
    public List<NewsArticleDetails> getLatestNews(String url) {
        return getArticles(url);
    }

    private List<NewsArticleDetails> getArticles(String pageUrl) {
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements newsArticles = doc.select("#tncms-region-index-one-bottom .card-top-story-list article");

            return newsArticles.stream()
                    .map(this::mapElementToArticle)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new ConnectException("An error occurred when connecting to the site.");
        }
    }

    private NewsArticleDetails mapElementToArticle(Element article) {
        final String labelFlag = article.selectFirst(".card-label-flags").text();
        final Element headlineElem = article.selectFirst(".tnt-headline a");
        final String headline = headlineElem.text();
        final String link = headlineElem.absUrl("href");
        final String lastUpdate = article.selectFirst("time").attr("datetime");

        return NewsArticleDetails.builder()
                .tags(labelFlag)
                .headline(headline)
                .datetime(lastUpdate)
                .url(link)
                .build();
    }
}
