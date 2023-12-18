package com.aarontan.DailyUpdates.service.impl;

import com.aarontan.DailyUpdates.pojos.sports.nba.Payload;
import com.aarontan.DailyUpdates.pojos.sports.nba.Standing;
import com.aarontan.DailyUpdates.service.NBAService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NBAServiceImpl implements NBAService {
    private final RestTemplate restTemplate;

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
