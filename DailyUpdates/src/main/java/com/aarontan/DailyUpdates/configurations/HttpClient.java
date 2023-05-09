package com.aarontan.DailyUpdates.configurations;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClient {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .build();
    }
}
