package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.request.LoginRequest;
import com.aarontan.DailyUpdates.payload.request.SignupRequest;

public interface AuthenticationService {
    void registerUser(SignupRequest signUpRequest);
    User authenticateUser(LoginRequest loginRequest);
    boolean validateJWT(String jwt);
}
