package com.aarontan.DailyUpdates.exceptions;

public class MunicipalityNotFoundException extends RuntimeException {
    public MunicipalityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
