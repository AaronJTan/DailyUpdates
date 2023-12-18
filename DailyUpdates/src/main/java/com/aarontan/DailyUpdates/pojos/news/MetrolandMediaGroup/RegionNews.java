package com.aarontan.DailyUpdates.pojos.news.MetrolandMediaGroup;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.aarontan.DailyUpdates.exceptions.ConnectException;
import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class RegionNews {
    private Document doc;

    public List<NewsArticleDetails> getLatestNews(String url) {
        navigateToNewsPage(url);
        Elements newsElems = getLatestNewsElems();

        return newsElems.stream()
                .map(this::mapElementToArticle)
                .collect(Collectors.toList());
    }

    private void navigateToNewsPage(String url) {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ConnectException("An error occurred");
        }

    }

    private Elements getLatestNewsElems() {
        return doc.select("#tncms-region-index-one-bottom .card-top-story-list article");
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
