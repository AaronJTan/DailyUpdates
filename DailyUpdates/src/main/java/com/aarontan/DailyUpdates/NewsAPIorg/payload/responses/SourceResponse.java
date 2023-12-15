package com.aarontan.DailyUpdates.NewsAPIorg.payload.responses;

import com.aarontan.DailyUpdates.NewsAPIorg.models.Source;
import lombok.Data;

import java.util.List;

@Data
public class SourceResponse {
    private String status;
    private List<Source> sources;
}
