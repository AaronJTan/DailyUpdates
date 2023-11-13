package com.aarontan.DailyUpdates.sports.nba.controllers;

import com.aarontan.DailyUpdates.response.ResponseObj;
import com.aarontan.DailyUpdates.sports.nba.pojos.Payload;
import com.aarontan.DailyUpdates.sports.nba.services.NBAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nba")
public class NBAController {
    private NBAService nbaService;
    public NBAController(NBAService nbaService) {
        this.nbaService = nbaService;
    }

    @GetMapping("/standings")
    public ResponseEntity<ResponseObj> getStandings() {
        Payload standings = nbaService.getConferenceStandings();

        return new ResponseObj.Builder()
                .setStatus(HttpStatus.OK)
                .setData(standings)
                .build();
    }
}
