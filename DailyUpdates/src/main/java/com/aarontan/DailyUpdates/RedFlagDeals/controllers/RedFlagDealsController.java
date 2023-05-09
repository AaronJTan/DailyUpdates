package com.aarontan.DailyUpdates.RedFlagDeals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;

import com.aarontan.DailyUpdates.RedFlagDeals.pojos.Deal;
import com.aarontan.DailyUpdates.RedFlagDeals.services.RedFlagDealsService;
import com.aarontan.DailyUpdates.response.ResponseObj;

@RestController
public class RedFlagDealsController {
    private RedFlagDealsService rfdService;

    @Autowired
    public RedFlagDealsController(RedFlagDealsService rfdService) {
        this.rfdService = rfdService;
    }

    @GetMapping("/hot-deals")
    public ResponseEntity<ResponseObj> getHotDeals() {
        List<Deal> deals = rfdService.getHotDeals();

        return new ResponseObj.Builder()
				.setStatus(HttpStatus.OK)
				.setData(deals)
				.build();
    }

    @ExceptionHandler({ RestClientResponseException.class })
    public ResponseEntity<ResponseObj> handleException(RestClientResponseException e) {
        // RFDError rfdError = e.getResponseBodyAs(RFDError.class);

        return new ResponseObj.Builder()
				.setStatus(HttpStatus.valueOf(e.getRawStatusCode()))
				// .setError(e.getResponseBodyAsString())
				.build();
    }
}