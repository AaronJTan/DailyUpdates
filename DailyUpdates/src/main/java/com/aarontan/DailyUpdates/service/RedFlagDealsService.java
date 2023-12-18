package com.aarontan.DailyUpdates.service;

import java.util.List;

import com.aarontan.DailyUpdates.pojos.deals.RedFlagDeals.Deal;

public interface RedFlagDealsService {
    public List<Deal> getHotDeals();
}
