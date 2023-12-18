package com.aarontan.DailyUpdates.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.pojos.news.NewsArticleDetails;
import com.aarontan.DailyUpdates.service.MetrolandMediaGroupNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarontan.DailyUpdates.constants.MetrolandMediaGroup.MunicipalityURLMap;
import com.aarontan.DailyUpdates.constants.MetrolandMediaGroup.RegionMunicipalityMap;
import com.aarontan.DailyUpdates.exceptions.MunicipalityNotFoundException;
import com.aarontan.DailyUpdates.utils.Util;

@RestController
@RequestMapping(path = "/metroland-media/")
public class MetrolandMediaGroupController {
    private final MetrolandMediaGroupNewsService metrolandMediaGroupNewsService;

    @Autowired
    public MetrolandMediaGroupController(MetrolandMediaGroupNewsService metrolandMediaGroupNewsService) {
        this.metrolandMediaGroupNewsService = metrolandMediaGroupNewsService;
    }

    @GetMapping("/all-areas")
    public ResponseEntity<ApiResponse> getAllAreas() {
        return new ResponseEntityBuilder()
            .setStatus(HttpStatus.OK)
            .setData(RegionMunicipalityMap.areas)
            .build();
    }

    @GetMapping("/regions")
    public ResponseEntity<ApiResponse> getRegions() {
        Map<String, String[]> regionMunicipality = RegionMunicipalityMap.areas;
        String[] regions = Util.getMapKeysAsArray(regionMunicipality);
        
        return new ResponseEntityBuilder()
            .setStatus(HttpStatus.OK)
            .setData(regions)
            .build();
    }

    @GetMapping("/{region}/municipalities")
    public ResponseEntity<ApiResponse> getRegionMunicipalities(@PathVariable String region) {
        String[] municipalities = RegionMunicipalityMap.areas.get(region);

        if (municipalities == null) {
            throw new MunicipalityNotFoundException("Region Not Found");
        }

        return new ResponseEntityBuilder()
            .setStatus(HttpStatus.OK)
            .setData(municipalities)
            .build();
    }

    @GetMapping("/news/{municipality}")
    public ResponseEntity<ApiResponse> getMunicipalityLatestNews(@PathVariable String municipality) {
        municipality = municipality.replaceAll("\\s", "").replace("-", " ");
        String url = MunicipalityURLMap.municipalities.get(municipality);

        if (url == null) {
            throw new MunicipalityNotFoundException("Municipality Not Found");
        }

        List<NewsArticleDetails> latestNews = metrolandMediaGroupNewsService.getLatestNews(url);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(latestNews)
                .build();
    }
}
