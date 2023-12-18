package com.aarontan.DailyUpdates.pojos.deals.RedFlagDeals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RFDError {
    private int status;
    private String message;
}
