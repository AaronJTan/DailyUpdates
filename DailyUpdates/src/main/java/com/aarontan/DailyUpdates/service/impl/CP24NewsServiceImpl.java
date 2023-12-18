package com.aarontan.DailyUpdates.service.impl;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import com.aarontan.DailyUpdates.service.ArticleWebScraper;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.aarontan.DailyUpdates.service.CP24NewsService;

@Service
public class CP24NewsServiceImpl extends ArticleWebScraper implements CP24NewsService {

    @Override
    public List<NewsArticleDetails> getLatestNews() {
        return getArticles("https://www.cp24.com/news");
    }

	@Override
	public List<NewsArticleDetails> getWorldNews() {
        return getArticles("https://www.cp24.com/world");
	}

    @Override
    protected String getNewsArticlesCSSSelectors() {
        return ".listInnerHorizontal .linklist li .teaserTitle";
    }

    @Override
    protected NewsArticleDetails mapElementToArticle(Element article) {
        Element articleAnchorElem = article.getElementsByTag("a").first();
        String headline = articleAnchorElem.text();
        String url = articleAnchorElem.attr("href");

        return NewsArticleDetails.builder()
                .headline(headline)
                .url(url)
                .build();
    }

    @Override
    protected String getConnectExceptionMessage() {
        return "An error occurred when connecting to CP24";
    }
}
