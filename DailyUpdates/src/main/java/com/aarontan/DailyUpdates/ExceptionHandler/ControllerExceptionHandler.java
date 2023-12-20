package com.aarontan.DailyUpdates.ExceptionHandler;

import com.aarontan.DailyUpdates.constants.ResponseMessage;
import com.aarontan.DailyUpdates.exceptions.*;
import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<ApiResponse> handleConflict(RuntimeException ex) {
        return new ResponseEntityBuilder()
            .setStatus(HttpStatus.NOT_FOUND)
            .setError(ex.getMessage())
            .build();
    }

    @ExceptionHandler(value = { HttpServerErrorException.class })
    public ResponseEntity<ApiResponse> handleHttpServerError(HttpServerErrorException ex) {
        return new ResponseEntityBuilder()
            .setStatus(ex.getStatusCode())
            .build();
    }

    @ExceptionHandler(value = { ConnectException.class })
    public ResponseEntity<ApiResponse> handleConnectException(ConnectException ex) {
        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.SERVICE_UNAVAILABLE)
                .setError(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = { HttpClientErrorException.class })
    public ResponseEntity<ApiResponse> handleHttpClientErrorException(HttpClientErrorException ex) {
        return new ResponseEntityBuilder()
                .setStatus(ex.getStatusCode())
                .build();
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.CONFLICT)
                .setError(ex.getMessage())
                .build();
    }

    @ExceptionHandler(DoesNotExistException.class)
    public ResponseEntity<ApiResponse> handleDoesNotExistException(DoesNotExistException ex) {
        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setMessage(ex.getMessage())
                .build();
    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    public ResponseEntity<ApiResponse> handleAccessDeniedException(Exception ex) {
        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.FORBIDDEN)
                .setMessage("You are not authorized to access this resource.")
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setError("Invalid request body.")
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setError(errors)
                .build();
    }

}