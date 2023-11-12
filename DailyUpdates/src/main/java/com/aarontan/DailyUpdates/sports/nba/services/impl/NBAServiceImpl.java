package com.aarontan.DailyUpdates.sports.nba.services.impl;

import com.aarontan.DailyUpdates.sports.nba.pojos.Payload;
import com.aarontan.DailyUpdates.sports.nba.pojos.Standing;
import com.aarontan.DailyUpdates.sports.nba.services.NBAService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NBAServiceImpl implements NBAService {
    private RestTemplate restTemplate;

    public NBAServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Payload getConferenceStandings() {
        final String url = "https://ca.global.nba.com/stats2/season/conferencestanding.json?locale=en";
        Standing standing = restTemplate.getForObject(url, Standing.class);

        return standing.getPayload();
    }
}
