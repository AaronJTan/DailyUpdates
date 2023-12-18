package com.aarontan.DailyUpdates.constants.MetrolandMediaGroup;

import java.util.Map;
import java.util.TreeMap;

public class MunicipalityURLMap {
    public static Map<String, String> municipalities = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        municipalities.putAll(DurhamRegionMunicipalities.municipalities);
        municipalities.putAll(HaltonRegionMunicipalities.municipalities);
        municipalities.putAll(PeelRegionMunicipalities.municipalities);
        municipalities.putAll(YorkRegionMunicipalities.municipalities);
    }
}
