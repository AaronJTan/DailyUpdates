package com.aarontan.DailyUpdates.controllers;

import com.aarontan.DailyUpdates.constants.ResponseMessage;
import com.aarontan.DailyUpdates.payload.request.LoginRequest;
import com.aarontan.DailyUpdates.payload.request.SignupRequest;
import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.JwtResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authenticationService.registerUser(signUpRequest);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setMessage(ResponseMessage.SUCCESSFUL_REGISTRATION)
                .build();

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String jwt = authenticationService.authenticateUser(loginRequest);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(new JwtResponse(jwt))
                .build();
    }

    @GetMapping("/validate-token")
    public ResponseEntity<ApiResponse> validateJWT(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
        }

        if (authenticationService.validateJWT(jwtToken)) {
            return new ResponseEntityBuilder()
                    .setStatus(HttpStatus.OK)
                    .build();
        }

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(ResponseMessage.INVALID_TOKEN)
                .build();
    }
}
