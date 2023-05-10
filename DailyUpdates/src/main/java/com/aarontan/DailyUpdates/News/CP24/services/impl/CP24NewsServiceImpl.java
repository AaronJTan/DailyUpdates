package com.aarontan.DailyUpdates.News.CP24.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.aarontan.DailyUpdates.News.CP24.pojos.Article;
import com.aarontan.DailyUpdates.News.CP24.services.CP24NewsService;

@Service
public class CP24NewsServiceImpl implements CP24NewsService {

    @Override
    public List<Article> getLatestNews() throws IOException {
        return getArticles("https://www.cp24.com/news");
    }

	@Override
	public List<Article> getWorldNews() throws IOException {
        return getArticles("https://www.cp24.com/world");
	}

    private List<Article> getArticles(String pageUrl) throws IOException {
        List<Article> articleList = new ArrayList<>();

        Document doc = Jsoup.connect(pageUrl).get();
        Elements newsArticles = doc.select(".listInnerHorizontal .linklist li .teaserTitle");
        for (Element article : newsArticles) {
            Element articleAnchorElem = article.getElementsByTag("a").first();
            System.out.println(articleAnchorElem);
            String headline = articleAnchorElem.text();
            String url = articleAnchorElem.attr("href");

            articleList.add(new Article(headline, url));

        }    

        return articleList;
    }
    
    
}
