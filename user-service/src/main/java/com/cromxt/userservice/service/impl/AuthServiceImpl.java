package com.cromxt.userservice.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cromxt.jwt.JWTService;
import com.cromxt.userservice.dtos.requests.UserCredentialDTO;
import com.cromxt.userservice.entity.CromUser;
import com.cromxt.userservice.entity.Tokens;
import com.cromxt.userservice.exception.InvalidUserCredentialsException;
import com.cromxt.userservice.repository.CromUserRepository;
import com.cromxt.userservice.repository.TokenRepository;
import com.cromxt.userservice.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CromUserRepository cromUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public Map<String, String> authenticate(UserCredentialDTO userCredentialDTO) {
        String username = userCredentialDTO.username();
        String password = userCredentialDTO.password();
        Optional<CromUser> cromUser = cromUserRepository.findByUsername(username);

        CromUser savedUser = cromUser.orElseThrow(() -> new InvalidUserCredentialsException("User not found"));

        boolean isValidPassword = passwordEncoder.matches(password, savedUser.getPassword());

        if (!isValidPassword) {
            throw new InvalidUserCredentialsException("Invalid user credentials");
        }

        Optional<Tokens> token = tokenRepository.findById(savedUser.getId());

        String accessToken = buildAccessToken(savedUser);
        final String refreshToken;

        if (!token.isPresent()) {
            refreshToken = jwtService.generateRefreshToken(savedUser);

            Tokens newToken = generateTokensEntity(savedUser, refreshToken);
            tokenRepository.save(newToken);
        }else{
            refreshToken = token.get().getRefreshToken();
        }

        return Map.of(
                "Authorization", accessToken,
                "Refresh-Token", refreshToken);
    }

    private Tokens generateTokensEntity(CromUser savedUser, String refreshToken) {
        return Tokens.builder()
                .cromUser(savedUser)
                .refreshToken(refreshToken)
                .build();
    }

    private String buildAccessToken(CromUser user) {
        return jwtService.generateAccessToken(user, new HashMap<>());
    }

    @Override
    public String gererateAccessToken(String username) {
        return null;
    }

}
