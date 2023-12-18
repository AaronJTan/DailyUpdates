package com.aarontan.DailyUpdates.service.impl;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import com.aarontan.DailyUpdates.service.ArticleWebScraper;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.aarontan.DailyUpdates.service.TheHackerNewsService;

@Service
public class TheHackerNewsServiceImpl extends ArticleWebScraper implements TheHackerNewsService {
    @Override
    public List<NewsArticleDetails> getLatestNews() {
        return getArticles("https://thehackernews.com");
    }

    @Override
    protected String getNewsArticlesCSSSelectors() {
        return "#Blog1 .body-post";
    }

    @Override
    protected NewsArticleDetails mapElementToArticle(Element article) {
        Element articleAnchorElem = article.getElementsByTag("a").first();

        if (articleAnchorElem.attr("rel").contains("sponsored")) {
            return null;
        }

        String headline = getHeadline(articleAnchorElem);
        String url = getArticleUrl(articleAnchorElem);
        String tags = getTags(articleAnchorElem);
        String dateTime = getDate(articleAnchorElem);

        return NewsArticleDetails.builder()
                .headline(headline)
                .url(url)
                .tags(tags)
                .datetime(dateTime)
                .build();
    }

    private String getHeadline(Element articleAnchorElem) {
        return articleAnchorElem.getElementsByClass("home-title").first().text();
    }

    private String getArticleUrl(Element articleAnchorElem) {
        return articleAnchorElem.attr("href");
    }

    private String getTags(Element articleAnchorElem) {
        Element tagsElem = articleAnchorElem.getElementsByClass("h-tags").first();
        return (tagsElem != null) ? tagsElem.text() : null;
    }

    private String getDate(Element articleAnchorElem) {
        Element dateTimeElem = articleAnchorElem.getElementsByClass("h-datetime").first();
        return (dateTimeElem != null) ? dateTimeElem.ownText() : null;
    }

    @Override
    protected String getConnectExceptionMessage() {
        return "An error occurred when connecting to The Hacker News";
    }
}
