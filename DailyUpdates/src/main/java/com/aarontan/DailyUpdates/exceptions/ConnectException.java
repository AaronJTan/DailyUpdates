package com.aarontan.DailyUpdates.exceptions;

public class ConnectException extends RuntimeException {
    public ConnectException(String errorMessage) {
        super(errorMessage);
    }
}
