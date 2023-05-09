package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.pojos;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegionNews {
    private final WebDriver driver;

    public List<Article> getLatestNews(String url) {
        navigateToNewsPage(url);
        List<WebElement> newsElems = getLatestNewsElems();
        List<Article> latestNews = new ArrayList<>();

        for (WebElement article : newsElems) {
            final String labelFlag = article.findElement(By.cssSelector(".card-label-flags")).getText();
            final WebElement headlineElem = article.findElement(By.cssSelector(".tnt-headline a"));
            final String headline = headlineElem.getText();
            final String link = headlineElem.getAttribute("href");
            final String lastUpdate = article.findElement(By.cssSelector("time")).getAttribute("datetime");
            
            latestNews.add(new Article(labelFlag, headline, lastUpdate, link));
        }
        
        return latestNews;
    }

    private void navigateToNewsPage(String url) {
        driver.get(url);
    }

    private List<WebElement> getLatestNewsElems() {
        List<WebElement> newsElems = driver.findElements(
            By.cssSelector("#tncms-region-index-one-bottom .card-top-story-list article")
        );

        return newsElems;
    }
}
