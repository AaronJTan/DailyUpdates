package com.aarontan.DailyUpdates.ExceptionHandler;

import com.aarontan.DailyUpdates.News.TheHackerNews.exceptions.HackerNewsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import com.aarontan.DailyUpdates.News.MetrolandMediaGroup.exceptions.MunicipalityNotFoundException;
import com.aarontan.DailyUpdates.response.ResponseObj;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = { MunicipalityNotFoundException.class })
    public ResponseEntity<ResponseObj> handleConflict(RuntimeException ex) {
        return new ResponseObj.Builder()
            .setStatus(HttpStatus.NOT_FOUND)
            .setError(ex.getMessage())
            .build();
    }

    @ExceptionHandler(value = { HttpServerErrorException.class })
    public ResponseEntity<ResponseObj> handleHttpServerError(RuntimeException ex) {
        return new ResponseObj.Builder()
            .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();
    }

    @ExceptionHandler(value = { HackerNewsException.class })
    public ResponseEntity<ResponseObj> handleHackerNewsException(RuntimeException ex) {
        return new ResponseObj.Builder()
                .setStatus(HttpStatus.SERVICE_UNAVAILABLE)
                .setError(ex.getMessage())
                .build();
    }
}