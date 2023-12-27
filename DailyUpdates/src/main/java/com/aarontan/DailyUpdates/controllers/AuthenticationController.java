package com.aarontan.DailyUpdates.controllers;

import com.aarontan.DailyUpdates.constants.ResponseMessage;
import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.request.LoginRequest;
import com.aarontan.DailyUpdates.payload.request.SignupRequest;
import com.aarontan.DailyUpdates.payload.response.ApiResponse;
import com.aarontan.DailyUpdates.payload.response.JwtResponse;
import com.aarontan.DailyUpdates.payload.response.ResponseEntityBuilder;
import com.aarontan.DailyUpdates.security.JwtUser;
import com.aarontan.DailyUpdates.service.AuthenticationService;
import com.aarontan.DailyUpdates.service.JwtService;
import com.aarontan.DailyUpdates.service.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
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
    public ResponseEntity<ApiResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        User user = authenticationService.authenticateUser(loginRequest);
        String jwt = jwtService.generateJwtToken(user);

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(UserMapper.convertToUserDTO(user))
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logoutUser(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
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

    @GetMapping("/whoami")
    public ResponseEntity<ApiResponse> validateJWT(Authentication authentication) {
        JwtUser user = (JwtUser) authentication.getPrincipal();

        return new ResponseEntityBuilder()
                .setStatus(HttpStatus.OK)
                .setData(user)
                .build();
    }
}
