package com.aarontan.DailyUpdates.RedFlagDeals.pojos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Deal {
    private int topicId;
    private String title;
    private String postTime;
    private String lastPostTime;    
    private String webPath;
    private Offer offer;

    public void setWebPath(String webPath) {
        this.webPath = "https://forums.redflagdeals.com" + webPath;
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Offer {
    private String dealerName;
    private String url;
    private String price;
    private String savings;
    private String expiresAt;
    
}