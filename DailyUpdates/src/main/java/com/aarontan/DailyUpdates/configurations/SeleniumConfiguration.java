package com.aarontan.DailyUpdates.configurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {
    @Bean
    public WebDriver driver() {
        System.setProperty("webdriver.chrome.driver","/home/auto/Desktop/DailyUpdates/gta-news-service/src/main/resources/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);

        return driver;
    } 
}
