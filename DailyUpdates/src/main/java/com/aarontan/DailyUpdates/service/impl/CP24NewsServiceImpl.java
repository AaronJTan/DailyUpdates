package com.aarontan.DailyUpdates.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.aarontan.DailyUpdates.exceptions.ConnectException;
import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.aarontan.DailyUpdates.service.CP24NewsService;

@Service
public class CP24NewsServiceImpl implements CP24NewsService {

    @Override
    public List<NewsArticleDetails> getLatestNews() {
        return getArticles("https://www.cp24.com/news");
    }

	@Override
	public List<NewsArticleDetails> getWorldNews() {
        return getArticles("https://www.cp24.com/world");
	}

    private List<NewsArticleDetails> getArticles(String pageUrl) {
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements newsArticles = doc.select(".listInnerHorizontal .linklist li .teaserTitle");

            return newsArticles.stream()
                    .map(this::mapElementToArticle)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new ConnectException("An error occurred when connecting to CP24");
        }
    }
    
    private NewsArticleDetails mapElementToArticle(Element article) {
        Element articleAnchorElem = article.getElementsByTag("a").first();
        String headline = articleAnchorElem.text();
        String url = articleAnchorElem.attr("href");

        return NewsArticleDetails.builder()
                .headline(headline)
                .url(url)
                .build();
    }
}
