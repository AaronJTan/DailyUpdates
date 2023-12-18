package com.aarontan.DailyUpdates.pojos.sports.nba;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamStanding {
    private int wins;
    private int losses;
    private Integer confRank;
}
