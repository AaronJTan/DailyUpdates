package com.aarontan.DailyUpdates.payload.response.NewsAPIorg;

import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.Source;
import lombok.Data;

import java.util.List;

@Data
public class SourceResponse {
    private String status;
    private List<Source> sources;
}
