package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.responses.UserResponse;
import com.cromxt.user.dtos.requests.UserCredential;
import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;
import com.cromxt.user.entity.Token;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.exceptions.InvalidUserDetailsException;
import com.cromxt.user.exceptions.UserNotFoundException;
import com.cromxt.user.repository.TokenRepository;
import com.cromxt.user.repository.UserEntityRepository;
import com.cromxt.user.service.AuthenticationService;
import com.cromxt.user.service.AuthorizationService;
import com.cromxt.user.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationAndAuthorizationServiceImpl implements AuthenticationService, AuthorizationService {
    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final JWTService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    public Map<String, String> authenticate(UserCredential request) {
//        AuthenticationManager done all the work for us and validate the credentials and fetch the data from database validate the password.
//        Todo: Enable Later
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        var user = userEntityRepository.findByUsername(request.username())
                .orElseThrow();
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
//        TODO: save the user Token.
        saveUserToken(user, refreshToken);
        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    @Override
    public Map<String, String> generateRefreshToken(String username) {
        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUserDetailsException("User with username: " + username + " not found"));

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, refreshToken);
        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    @Override
    public String generateAccessToken(String token) {
        String username = jwtService.extractUsername(token);
        return jwtService.generateToken(UserEntity.builder().username(username).build());
    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }




    @Override
    public UserResponse getUserDetails(UserCredential userCredential) {

        final String username = userCredential.username();
//        TODO: Enable Later
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        userCredential.password()
                )
        );
        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
//        If necessary then add more details.
        return new UserResponse(user.getUsername());
    }
}
