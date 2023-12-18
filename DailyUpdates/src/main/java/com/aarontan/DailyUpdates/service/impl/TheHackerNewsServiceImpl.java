package com.aarontan.DailyUpdates.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.aarontan.DailyUpdates.exceptions.ConnectException;
import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.aarontan.DailyUpdates.service.TheHackerNewsService;

@Service
public class TheHackerNewsServiceImpl implements TheHackerNewsService {
    @Override
    public List<NewsArticleDetails> getLatestNews()  {
        try {
            Document doc = Jsoup.connect("https://thehackernews.com").get();
            Elements newsArticles = doc.select("#Blog1 .body-post");

            return newsArticles.stream()
                    .map(this::buildArticle)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ConnectException("An error occurred when connecting to The Hacker News");
        }
    }

    private NewsArticleDetails buildArticle(Element article) {
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
}
