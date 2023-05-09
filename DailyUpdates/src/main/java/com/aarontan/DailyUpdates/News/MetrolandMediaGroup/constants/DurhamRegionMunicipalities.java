package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.constants;

import java.util.Map;
import java.util.TreeMap;

public class DurhamRegionMunicipalities {
    public static Map<String, String> municipalities = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        municipalities.put("Ajax", "https://www.durhamregion.com/ontario-communities/ajax/");
        municipalities.put("Brock", "https://www.durhamregion.com/ontario-communities/brock/");
        municipalities.put("Clarington", "https://www.durhamregion.com/ontario-communities/clarington/");
        municipalities.put("Oshawa", "https://www.durhamregion.com/ontario-communities/oshawa/");
        municipalities.put("Pickering", "https://www.durhamregion.com/ontario-communities/pickering/");
        municipalities.put("Port Perry", "https://www.durhamregion.com/ontario-communities/port-perry/");
        municipalities.put("Uxbridge", "https://www.durhamregion.com/ontario-communities/uxbridge/");
        municipalities.put("Whitby", "https://www.durhamregion.com/ontario-communities/whitby/");
    }
}


