package com.aarontan.DailyUpdates.ExceptionHandler;

import com.aarontan.DailyUpdates.constants.ResponseMessage;
import com.aarontan.DailyUpdates.exceptions.ConnectException;
import com.aarontan.DailyUpdates.exceptions.UserRegistrationException;
import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.aarontan.DailyUpdates.exceptions.MunicipalityNotFoundException;
import com.aarontan.DailyUpdates.response.ResponseObj;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<ApiResponse> handleUserRegistrationException(UserRegistrationException ex) {
        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.CONFLICT)
                .setMessage(ex.getMessage())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(ResponseMessage.INVALID_CREDENTIALS)
                .build();
    }

    @ExceptionHandler(value = { MunicipalityNotFoundException.class })
    public ResponseEntity<ResponseObj> handleConflict(RuntimeException ex) {
        return new ResponseObj.Builder()
            .setStatus(HttpStatus.NOT_FOUND)
            .setError(ex.getMessage())
            .build();
    }

    @ExceptionHandler(value = { HttpServerErrorException.class })
    public ResponseEntity<ResponseObj> handleHttpServerError(HttpServerErrorException ex) {
        return new ResponseObj.Builder()
            .setStatus(ex.getStatusCode())
            .build();
    }

    @ExceptionHandler(value = { ConnectException.class })
    public ResponseEntity<ResponseObj> handleConnectException(RuntimeException ex) {
        return new ResponseObj.Builder()
                .setStatus(HttpStatus.SERVICE_UNAVAILABLE)
                .setError(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = { HttpClientErrorException.class })
    public ResponseEntity<ResponseObj> handleHttpClientErrorException(HttpClientErrorException ex) {
        return new ResponseObj.Builder()
                .setStatus(ex.getStatusCode())
                .build();
    }
}