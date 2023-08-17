package com.aarontan.DailyUpdates.RedFlagDeals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.aarontan.DailyUpdates.RedFlagDeals.pojos.Deal;
import com.aarontan.DailyUpdates.RedFlagDeals.pojos.RFDError;
import com.aarontan.DailyUpdates.RedFlagDeals.services.RedFlagDealsService;
import com.aarontan.DailyUpdates.response.ResponseObj;
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
    public ResponseEntity<ResponseObj> getHotDeals() {
        List<Deal> deals = rfdService.getHotDeals();

        return new ResponseObj.Builder()
				.setStatus(HttpStatus.OK)
				.setData(deals)
				.build();
    }

    @ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<ResponseObj> handleException(HttpClientErrorException e) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RFDError rfdError = objectMapper.readValue(e.getResponseBodyAsString(), RFDError.class);
        return new ResponseObj.Builder()
				.setStatus(HttpStatus.valueOf(rfdError.getStatus()))
				.setError(rfdError.getMessage())
				.build();
    }
}