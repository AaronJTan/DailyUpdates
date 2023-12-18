package com.aarontan.DailyUpdates.service.impl;

import com.aarontan.DailyUpdates.exceptions.UserRegistrationException;
import com.aarontan.DailyUpdates.models.Role;
import com.aarontan.DailyUpdates.models.RoleType;
import com.aarontan.DailyUpdates.models.User;
import com.aarontan.DailyUpdates.payload.request.LoginRequest;
import com.aarontan.DailyUpdates.payload.request.SignupRequest;
import com.aarontan.DailyUpdates.repository.RoleRepository;
import com.aarontan.DailyUpdates.repository.UserRepository;
import com.aarontan.DailyUpdates.service.AuthenticationService;
import com.aarontan.DailyUpdates.service.JwtService;
import com.aarontan.DailyUpdates.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(JwtService jwtService, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 UserRepository userRepository,
                                 RoleRepository roleRepository
    ) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public void registerUser(SignupRequest signUpRequest) {
        final String username = signUpRequest.getUsername();
        final String email = signUpRequest.getEmail();
        validateUserDoesNotExists(username, email);
        final String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        User user = new User(username, email, encodedPassword);
        assignUserRole(user);
        userRepository.save(user);
    }

    private void validateUserDoesNotExists(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new UserRegistrationException("Username is already taken.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException("Email is already in use.");
        }
    }

    private void assignUserRole(User user) {
        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        user.setRoles(Collections.singleton(userRole));
    }

    @Override
    public String authenticateUser(LoginRequest loginRequest) {
        String usernameOrEmail = loginRequest.getUsernameOrEmail();
        String username = !EmailValidator.isValidEmail(usernameOrEmail) ?
                usernameOrEmail : userRepository.findUsernameByEmail(usernameOrEmail);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        loginRequest.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        return jwtService.generateJwtToken(user);
    }

    @Override
    public boolean validateJWT(String jwt) {
        return jwtService.validateJwt(jwt);
    }
}
