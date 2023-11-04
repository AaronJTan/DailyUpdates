package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.pojos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class RegionNews {
    private Document doc;

    public List<Article> getLatestNews(String url) throws IOException {
        navigateToNewsPage(url);
        Elements newsElems = getLatestNewsElems();

        return newsElems.stream()
                .map(this::mapElementToArticle)
                .collect(Collectors.toList());
    }

    private void navigateToNewsPage(String url) throws IOException {
        doc = Jsoup.connect(url).get();
    }

    private Elements getLatestNewsElems() {
        return doc.select("#tncms-region-index-one-bottom .card-top-story-list article");
    }

    private Article mapElementToArticle(Element article) {
        final String labelFlag = article.selectFirst(".card-label-flags").text();
        final Element headlineElem = article.selectFirst(".tnt-headline a");
        final String headline = headlineElem.text();
        final String link = headlineElem.absUrl("href");
        final String lastUpdate = article.selectFirst("time").attr("datetime");

        return new Article(labelFlag, headline, lastUpdate, link);
    }
}
