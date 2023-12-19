package com.aarontan.DailyUpdates.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedSourceRequest {
    @NotBlank(message = "Source id is required")
    private String sourceId;
}