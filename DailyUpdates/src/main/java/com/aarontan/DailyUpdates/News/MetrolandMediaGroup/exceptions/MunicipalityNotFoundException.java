package com.aarontan.DailyUpdates.News.MetrolandMediaGroup.exceptions;

public class MunicipalityNotFoundException extends RuntimeException {
    public MunicipalityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
