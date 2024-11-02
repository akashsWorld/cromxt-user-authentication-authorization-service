package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.requests.LoginUserDTO;
import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;
import com.cromxt.user.entity.Token;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.repository.TokenRepository;
import com.cromxt.user.repository.UserEntityRepository;
import com.cromxt.user.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final JWTService jwtService;
    private final TokenRepository tokenRepository;

    public AuthenticationResponseDTO authenticate(LoginUserDTO request) {
//        AuthenticationManager done all the work for us and validate the credentials and fetch the data from database validate the password.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        var user = userEntityRepository.findByUsername(request.username())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponseDTO(jwtToken);
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

}
