package com.aarontan.DailyUpdates.service.impl;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import com.aarontan.DailyUpdates.service.ArticleWebScraper;
import com.aarontan.DailyUpdates.service.MetrolandMediaGroupNewsService;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class MetrolandMediaGroupNewsServiceImpl extends ArticleWebScraper implements MetrolandMediaGroupNewsService {
    @Override
    public List<NewsArticleDetails> getLatestNews(String url) {
        return getArticles(url);
    }

    @Override
    protected String getNewsArticlesCSSSelectors() {
        return "#tncms-region-index-one-bottom .card-top-story-list article";
    }

    @Override
    protected NewsArticleDetails mapElementToArticle(Element article) {
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

    @Override
    protected String getConnectExceptionMessage() {
        return "An error occurred when connecting to the site.";
    }
}
