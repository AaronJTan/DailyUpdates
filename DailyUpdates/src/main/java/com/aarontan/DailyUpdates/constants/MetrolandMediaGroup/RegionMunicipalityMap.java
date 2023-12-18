package com.aarontan.DailyUpdates.constants.MetrolandMediaGroup;

import java.util.Map;
import java.util.TreeMap;

import com.aarontan.DailyUpdates.utils.Util;

public class RegionMunicipalityMap {
    public static Map<String, String[]> areas = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        areas.put(RegionName.DURHAM, Util.getMapKeysAsArray(DurhamRegionMunicipalities.municipalities));
        areas.put(RegionName.HALTON, Util.getMapKeysAsArray(HaltonRegionMunicipalities.municipalities));
        areas.put(RegionName.PEEL, Util.getMapKeysAsArray(PeelRegionMunicipalities.municipalities));
        areas.put(RegionName.YORK, Util.getMapKeysAsArray(YorkRegionMunicipalities.municipalities));
    }
}
