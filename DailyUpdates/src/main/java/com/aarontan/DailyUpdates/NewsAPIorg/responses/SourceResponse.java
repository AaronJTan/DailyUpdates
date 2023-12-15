package com.aarontan.DailyUpdates.NewsAPIorg.responses;

import com.aarontan.DailyUpdates.NewsAPIorg.models.Source;
import lombok.Data;

import java.util.List;

@Data
public class SourceResponse {
    private String status;
    private List<Source> sources;
}
