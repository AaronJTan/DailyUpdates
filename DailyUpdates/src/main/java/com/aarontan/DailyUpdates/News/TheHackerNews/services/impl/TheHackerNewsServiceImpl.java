package com.aarontan.DailyUpdates.News.TheHackerNews.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.aarontan.DailyUpdates.News.TheHackerNews.pojos.Article;
import com.aarontan.DailyUpdates.News.TheHackerNews.services.TheHackerNewsService;

@Service
public class TheHackerNewsServiceImpl implements TheHackerNewsService {

    @Override
    public List<Article> getLatestNews() throws IOException {
        return getArticles("https://thehackernews.com/");
    }

    private List<Article> getArticles(String pageUrl) throws IOException {
        List<Article> articleList = new ArrayList<>();

        Document doc = Jsoup.connect(pageUrl).get();
        Elements newsArticles = doc.select("#Blog1 .body-post");
        for (Element article : newsArticles) {
            Element articleAnchorElem = article.getElementsByTag("a").first();

            if (articleAnchorElem.attr("rel").contains("sponsored")) {
                continue;
            }

            String headline = articleAnchorElem.getElementsByClass("home-title").first().text();
            String url = articleAnchorElem.attr("href");
            String tags = articleAnchorElem.getElementsByClass("h-tags").first().text();
            String dateTime = articleAnchorElem.getElementsByClass("h-datetime").first().ownText();

            articleList.add(new Article(headline, url, tags, dateTime));

        }    

        return articleList;
    }
    
    
}
