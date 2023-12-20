package com.aarontan.DailyUpdates.service.impl;

import com.aarontan.DailyUpdates.models.TopSource;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.ArticleResponse;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.SourceResponse;
import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.aarontan.DailyUpdates.repository.*;
import com.aarontan.DailyUpdates.service.NewsAPIService;
import com.aarontan.DailyUpdates.service.SourceMapper;
import com.aarontan.DailyUpdates.utils.UriParamsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsAPIServiceImpl implements NewsAPIService {
    private final RestTemplate restTemplate;
    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;

    @Value("${NewsAPIKey}")
    private String newsAPIKey;

    public NewsAPIServiceImpl(RestTemplate restTemplate, SourceRepository sourceRepository
            , SourceMapper sourceMapper) {
        this.restTemplate = restTemplate;
        this.sourceRepository = sourceRepository;
        this.sourceMapper = sourceMapper;
    }

    private UriComponentsBuilder initUriComponentsBuilder() {
        return UriComponentsBuilder
                .fromHttpUrl("https://newsapi.org/v2");
    }

    @Scheduled(fixedRate = 86400000)
    public void updateTopSources() {
        System.out.println("Updating database with top sources");
        HttpEntity<String> httpEntity = createHttpEntityWithHeaders();

        UriComponentsBuilder uriComponentsBuilder = initUriComponentsBuilder()
                .path("/top-headlines/sources");

        SourceResponse sourceResponse = restTemplate.exchange(uriComponentsBuilder.build().toUri(),
                HttpMethod.GET,
                httpEntity,
                SourceResponse.class).getBody();

        List<TopSource> sourceList = new ArrayList<>();

        for (SourceAPIModel source: sourceResponse.getSourceAPIModels()) {
            TopSource topSource = sourceMapper.convertToTopSource(source);
            sourceList.add(topSource);
        }
        sourceRepository.saveAll(sourceList);

        System.out.println("Source update complete.");
    }

    @Override
    public SourceResponse getTopSources(Map<String, String> queryParams) {
        Specification<TopSource> spec = Specification.where(
                TopSourceSpecification.withCategoryName(queryParams.get("category"))
                        .and(TopSourceSpecification.withLanguageCode(queryParams.get("language")))
                        .and(TopSourceSpecification.withCountryCode(queryParams.get("country")))
        );

        List<TopSource> sources = sourceRepository.findAll(spec);

        return sourceMapper.convertToSourceResponse(sources);
    }

    @Override
    public ArticleResponse getTopHeadlines(Map<String, String> queryParams) {
        HttpEntity<String> httpEntity = createHttpEntityWithHeaders();

        UriComponentsBuilder uriComponentsBuilder = initUriComponentsBuilder()
                .path("/top-headlines");

        final String[] validQueryParams = new String[]{"country", "category", "sources", "q", "pageSize", "page"};
        UriParamsBuilder uriParamsBuilder = new UriParamsBuilder(uriComponentsBuilder, validQueryParams, queryParams);
        uriComponentsBuilder = uriParamsBuilder.addValidQueryParamsToUriComponentsBuilder();

        return restTemplate.exchange(uriComponentsBuilder.build().toUri(),
                HttpMethod.GET,
                httpEntity,
                ArticleResponse.class).getBody();
    }

    @Override
    public ArticleResponse getEverything(Map<String, String> queryParams) {
        HttpEntity<String> httpEntity = createHttpEntityWithHeaders();

        UriComponentsBuilder uriComponentsBuilder = initUriComponentsBuilder()
                .path("/everything");

        final String[] validQueryParams = new String[]{"q", "searchIn", "sources", "domains", "excludeDomains", "from",
                "to", "language", "sortBy", "pageSize", "page"};
        UriParamsBuilder uriParamsBuilder = new UriParamsBuilder(uriComponentsBuilder, validQueryParams, queryParams);
        uriComponentsBuilder = uriParamsBuilder.addValidQueryParamsToUriComponentsBuilder();

        return restTemplate.exchange(uriComponentsBuilder.build().toUri(),
                HttpMethod.GET,
                httpEntity,
                ArticleResponse.class).getBody();
    }

    private HttpEntity<String> createHttpEntityWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", newsAPIKey);
        return new HttpEntity<>(headers);
    }
}
