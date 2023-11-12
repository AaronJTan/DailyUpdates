package com.aarontan.DailyUpdates.sports.nba.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConferenceStandings {
    private List<Team> teams;
    private String conference;
    private String displayConference;
    private String displayDivision;
    private String division;

    public void setTeams(List<Team> teams) {
        teams.sort((Team t1, Team t2) ->
                t1.getConferenceRank().
                        compareTo(t2.getStandings().getConfRank())
        );
        this.teams = teams;
    }
}
