package com.aarontan.DailyUpdates.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aarontan.DailyUpdates.pojos.deals.RedFlagDeals.Deal;
import com.aarontan.DailyUpdates.pojos.deals.RedFlagDeals.DealList;
import com.aarontan.DailyUpdates.service.RedFlagDealsService;

@Service
public class RedFlagDealsServiceImpl implements RedFlagDealsService {
    private final RestTemplate restTemplate;

    @Autowired
    public RedFlagDealsServiceImpl(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
    }

    @Override
    public List<Deal> getHotDeals() {
        DealList deals = restTemplate.getForObject("https://forums.redflagdeals.com/api/topics?forum_id=9&per_page=30&page=1", DealList.class);

        return deals.getTopics();
    }
}
