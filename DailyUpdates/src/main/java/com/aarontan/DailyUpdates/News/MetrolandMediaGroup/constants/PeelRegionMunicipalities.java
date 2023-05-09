package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.constants;

import java.util.Map;
import java.util.TreeMap;

public class PeelRegionMunicipalities {
    public static Map<String, String> municipalities = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    
    static {
        municipalities.put("Mississauga", "https://www.mississauga.com/news/");
        municipalities.put("Brampton", "https://www.bramptonguardian.com/news/");
        municipalities.put("Caledon", "https://www.caledonenterprise.com/news/");
    }
}
