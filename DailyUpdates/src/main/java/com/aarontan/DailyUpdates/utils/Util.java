package com.aarontan.DailyUpdates.utils;

import java.util.Map;

public class Util {
    public static String[] getMapKeysAsArray(Map<String, ?> map) {
        return map.keySet().toArray(new String[0]);
    }
}
