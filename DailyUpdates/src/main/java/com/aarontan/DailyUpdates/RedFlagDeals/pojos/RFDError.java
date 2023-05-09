package com.aarontan.DailyUpdates.RedFlagDeals.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RFDError {
    private int status;
    private String message;
}
