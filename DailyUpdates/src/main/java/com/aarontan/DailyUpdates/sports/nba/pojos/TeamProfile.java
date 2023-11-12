package com.aarontan.DailyUpdates.sports.nba.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamProfile {
    private String abbr;
    private String city;
    private String cityEn;
    private String code;
    private String conference;
    private String displayAbbr;
    private String displayConference;
    private String division;
    private String id;
    private String isAllStarTeam;
    private String isLeagueTeam;
    private String leagueId;
    private String name;
    private String nameEn;
}
