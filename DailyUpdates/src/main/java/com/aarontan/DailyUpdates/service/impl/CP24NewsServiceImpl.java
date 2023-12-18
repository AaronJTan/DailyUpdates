package com.aarontan.DailyUpdates.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.aarontan.DailyUpdates.exceptions.ConnectException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.aarontan.DailyUpdates.pojos.news.cp24.Article;
import com.aarontan.DailyUpdates.service.CP24NewsService;

@Service
public class CP24NewsServiceImpl implements CP24NewsService {

    @Override
    public List<Article> getLatestNews() {
        return getArticles("https://www.cp24.com/news");
    }

	@Override
	public List<Article> getWorldNews() {
        return getArticles("https://www.cp24.com/world");
	}

    private List<Article> getArticles(String pageUrl) {
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements newsArticles = doc.select(".listInnerHorizontal .linklist li .teaserTitle");

            return newsArticles.stream()
                    .map(this::mapElementToArticle)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new ConnectException("An error occurred when connecting to The Hacker News");
        }
    }
    
    private Article mapElementToArticle(Element article) {
        Element articleAnchorElem = article.getElementsByTag("a").first();
        String headline = articleAnchorElem.text();
        String url = articleAnchorElem.attr("href");

        return new Article(headline, url);
    }
}
