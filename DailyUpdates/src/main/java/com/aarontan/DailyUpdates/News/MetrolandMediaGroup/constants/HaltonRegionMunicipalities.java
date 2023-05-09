package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.constants;

import java.util.Map;
import java.util.TreeMap;

public class HaltonRegionMunicipalities {
    public static Map<String, String> municipalities = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        municipalities.put("Burlington", "https://www.insidehalton.com/ontario-communities/burlington/");
        municipalities.put("Milton", "https://www.insidehalton.com/ontario-communities/milton/");
        municipalities.put("Oakville", "https://www.insidehalton.com/ontario-communities/oakville/");
    }
}
