package com.aarontan.DailyUpdates.controllers;

import java.util.List;

import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.aarontan.DailyUpdates.pojos.deals.RedFlagDeals.Deal;
import com.aarontan.DailyUpdates.pojos.deals.RedFlagDeals.RFDError;
import com.aarontan.DailyUpdates.service.RedFlagDealsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/rfd")
public class RedFlagDealsController {
    private RedFlagDealsService rfdService;

    @Autowired
    public RedFlagDealsController(RedFlagDealsService rfdService) {
        this.rfdService = rfdService;
    }

    @GetMapping("/hot-deals")
    public ResponseEntity<ApiResponse> getHotDeals() {
        List<Deal> deals = rfdService.getHotDeals();

        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.OK)
				.setData(deals)
				.build();
    }

    @ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<ApiResponse> handleException(HttpClientErrorException e) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RFDError rfdError = objectMapper.readValue(e.getResponseBodyAsString(), RFDError.class);
        return new ResponseEntityBuilder()
				.setStatus(HttpStatus.valueOf(rfdError.getStatus()))
				.setError(rfdError.getMessage())
				.build();
    }
}