package com.aarontan.DailyUpdates.utils;

import lombok.AllArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class UriParamsBuilder {
    private UriComponentsBuilder uriComponentsBuilder;
    private String[] validQueryParams;
    private Map<String, String> queryParams;

    public UriComponentsBuilder addValidQueryParamsToUriComponentsBuilder() {
        Arrays.stream(validQueryParams).forEach((param) ->
                uriComponentsBuilder.queryParamIfPresent(param,
                        Optional.ofNullable(queryParams.get(param)).filter(s -> !s.isEmpty())
                )
        );

        return uriComponentsBuilder;
    }
}
