package com.aarontan.DailyUpdates.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.aarontan.DailyUpdates.pojos.news.MetrolandMediaGroup.Article;
import com.aarontan.DailyUpdates.pojos.news.MetrolandMediaGroup.RegionNews;
import com.aarontan.DailyUpdates.response.ResponseObj;
import com.aarontan.DailyUpdates.utils.Util;

@RestController
@RequestMapping(path = "/metroland-media/")
public class MetrolandMediaGroupController {
    private final RegionNews regionalNews;

    @Autowired
    public MetrolandMediaGroupController(RegionNews regionalNews) {
        this.regionalNews = regionalNews;
    }

    @GetMapping("/all-areas")
    public ResponseEntity<ResponseObj> getAllAreas() {
        return new ResponseObj.Builder()
            .setStatus(HttpStatus.OK)
            .setData(RegionMunicipalityMap.areas)
            .build();
    }

    @GetMapping("/regions")
    public ResponseEntity<ResponseObj> getRegions() {
        Map<String, String[]> regionMunicipality = RegionMunicipalityMap.areas;
        String[] regions = Util.getMapKeysAsArray(regionMunicipality);
        
        return new ResponseObj.Builder()
            .setStatus(HttpStatus.OK)
            .setData(regions)
            .build();
    }

    @GetMapping("/{region}/municipalities")
    public ResponseEntity<ResponseObj> getRegionMunicipalities(@PathVariable String region) {
        String[] municipalities = RegionMunicipalityMap.areas.get(region);

        if (municipalities == null) {
            throw new MunicipalityNotFoundException("Region Not Found");
        }

        return new ResponseObj.Builder()
            .setStatus(HttpStatus.OK)
            .setData(municipalities)
            .build();
    }

    @GetMapping("/news/{municipality}")
    public List<Article> getMunicipalityLatestNews(@PathVariable String municipality) throws IOException {
        municipality = municipality.replaceAll("\\s", "").replace("-", " ");
        String url = MunicipalityURLMap.municipalities.get(municipality);

        if (url == null) {
            throw new MunicipalityNotFoundException("Municipality Not Found");
        }

        return regionalNews.getLatestNews(url);
    }
}
