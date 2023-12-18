package com.aarontan.DailyUpdates.controllers;

import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.pojos.sports.nba.Payload;
import com.aarontan.DailyUpdates.service.NBAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nba")
public class NBAController {
    private final NBAService nbaService;
    public NBAController(NBAService nbaService) {
        this.nbaService = nbaService;
    }

    @GetMapping("/standings")
    public ResponseEntity<ApiResponse> getStandings() {
        Payload standings = nbaService.getConferenceStandings();

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(standings)
                .build();
    }
}
