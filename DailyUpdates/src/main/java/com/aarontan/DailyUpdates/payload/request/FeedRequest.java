package com.aarontan.DailyUpdates.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequest {
    @NotBlank(message = "Feed name is required")
    private String name;
}