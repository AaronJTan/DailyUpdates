package com.aarontan.DailyUpdates.sports.nba.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Team {
    private TeamProfile profile;
    private TeamStanding standings;

    public Integer getConferenceRank() {
        return this.standings.getConfRank();
    }
}
