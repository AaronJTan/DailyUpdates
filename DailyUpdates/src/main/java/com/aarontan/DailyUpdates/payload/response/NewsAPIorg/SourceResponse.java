package com.aarontan.DailyUpdates.payload.response.NewsAPIorg;

import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SourceResponse {
    private String status;
    @JsonProperty("sources")
    private List<SourceAPIModel> sourceAPIModels;
}
