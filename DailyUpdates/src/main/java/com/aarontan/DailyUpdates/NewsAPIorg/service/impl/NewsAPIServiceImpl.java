package com.aarontan.DailyUpdates.NewsAPIorg.service.impl;

import com.aarontan.DailyUpdates.NewsAPIorg.payload.responses.ArticleResponse;
import com.aarontan.DailyUpdates.NewsAPIorg.payload.responses.SourceResponse;
import com.aarontan.DailyUpdates.NewsAPIorg.service.NewsAPIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

@Service
public class NewsAPIServiceImpl implements NewsAPIService {
    private RestTemplate restTemplate;

    @Value("${NewsAPIKey}")
    private String newsAPIKey;

    public NewsAPIServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://newsapi.org/v2/").build();
    }

    private UriComponentsBuilder initUriComponentsBuilder() {
        return UriComponentsBuilder
                .fromHttpUrl("https://newsapi.org/v2");
    }

    @Override
    public SourceResponse getTopSources(Map<String, String> queryParams) {
        HttpEntity<String> httpEntity = createHttpEntityWithHeaders();

        UriComponentsBuilder uriComponentsBuilder = initUriComponentsBuilder()
                .path("/top-headlines/sources")
                .queryParamIfPresent("category", Optional.ofNullable(queryParams.get("category")))
                .queryParamIfPresent("language", Optional.ofNullable(queryParams.get("language")))
                .queryParamIfPresent("country", Optional.ofNullable(queryParams.get("country")));

        return restTemplate.exchange(uriComponentsBuilder.build().toUri(),
                HttpMethod.GET,
                httpEntity,
                SourceResponse.class).getBody();
    }

    @Override
    public ArticleResponse getTopHeadlines(Map<String, String> queryParams) {
        HttpEntity<String> httpEntity = createHttpEntityWithHeaders();

        UriComponentsBuilder uriComponentsBuilder = initUriComponentsBuilder()
                .path("/top-headlines")
                .queryParamIfPresent("country", Optional.ofNullable(queryParams.get("country")))
                .queryParamIfPresent("category", Optional.ofNullable(queryParams.get("category")))
                .queryParamIfPresent("sources", Optional.ofNullable(queryParams.get("sources")))
                .queryParamIfPresent("q", Optional.ofNullable(queryParams.get("q")))
                .queryParamIfPresent("pageSize", Optional.ofNullable(queryParams.get("pageSize")))
                .queryParamIfPresent("page", Optional.ofNullable(queryParams.get("page")));


        return restTemplate.exchange(uriComponentsBuilder.build().toUri(),
                HttpMethod.GET,
                httpEntity,
                ArticleResponse.class).getBody();
    }

    private HttpEntity<String> createHttpEntityWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", newsAPIKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        return httpEntity;
    }
}
