package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.constants.pojos;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommunityScraper {
    private final WebDriver driver;
    
    public List<Community> getCommunities(String regionUrl) {
        navigateToNewsPage(regionUrl);
        List<WebElement> communityElems = getCommunityElems();
        List<Community> communities = new ArrayList<>();
        
        for (WebElement community : communityElems) {
            final String name = community
                .findElement(By.cssSelector(".nav-label"))
                .getAttribute("innerText")
                .strip();
            final String link = community.getAttribute("href");
            communities.add(new Community(name, link));
        }
        
        return communities;
    }

    private void navigateToNewsPage(String regionUrl) {
        driver.get(regionUrl);
    }

    private List<WebElement> getCommunityElems() {
        List<WebElement> communityElements = driver.findElements(
            By.cssSelector("#main-nav_menu-subdrop-3 li a")
        );

        return communityElements;
    }
}
