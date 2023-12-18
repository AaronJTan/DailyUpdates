package com.aarontan.DailyUpdates.constants.MetrolandMediaGroup;

import java.util.Map;
import java.util.TreeMap;

public class YorkRegionMunicipalities {

    public static Map<String, String> municipalities = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        municipalities.put("Aurora", "https://www.yorkregion.com/ontario-communities/aurora/");
        municipalities.put("East Gwillimbury", "https://www.yorkregion.com/ontario-communities/east-gwillimbury/");
        municipalities.put("Georgina", "https://www.yorkregion.com/ontario-communities/georgina/");
        municipalities.put("King", "https://www.yorkregion.com/ontario-communities/king/");
        municipalities.put("Markham", "https://www.yorkregion.com/ontario-communities/markham/");
        municipalities.put("Newmarket", "https://www.yorkregion.com/ontario-communities/newmarket/");
        municipalities.put("Richmond Hill", "https://www.yorkregion.com/ontario-communities/richmond-hill/");
        municipalities.put("Stouffville", "https://www.yorkregion.com/ontario-communities/stouffville/");
        municipalities.put("Thornhill", "https://www.yorkregion.com/ontario-communities/thornhill/");
        municipalities.put("Vaughan", "https://www.yorkregion.com/ontario-communities/vaughan/");
    }
}