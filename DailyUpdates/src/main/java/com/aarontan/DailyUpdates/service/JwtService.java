package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.models.User;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateJwtToken(User user);
    boolean validateJwt(String jwt);
    Claims getJwtClaims(String jwt);
}
