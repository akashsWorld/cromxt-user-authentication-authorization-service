package com.cromxt.user.service.impl;

import com.cromxt.cromspace.dtos.UserResponse;
import com.cromxt.cromspace.dtos.UserValidationDTO;
import com.cromxt.user.dtos.requests.LoginUserDTO;
import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;
import com.cromxt.user.entity.Token;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.exceptions.UserNotFoundException;
import com.cromxt.user.repository.TokenRepository;
import com.cromxt.user.repository.UserEntityRepository;
import com.cromxt.user.service.AuthenticationService;
import com.cromxt.user.service.AuthorizationService;
import com.cromxt.user.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationAndAuthorizationServiceImpl implements AuthenticationService, AuthorizationService {
    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final JWTService jwtService;
    private final TokenRepository tokenRepository;

    @Override
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


    @Override
    public Boolean isRequestValid(UserValidationDTO userValidationDTO) {
        if(userValidationDTO.username() == null || userValidationDTO.username().isEmpty()){
            return false;
        }
        if (userValidationDTO.token() == null || userValidationDTO.token().isEmpty()) {
            return false;
        }
        String username = jwtService.extractUsername(
                userValidationDTO
                .token()
                .substring(7));
        return username != null && username.equals(userValidationDTO.username());
    }

    @Override
    public UserResponse getUserDetails(String token) {
        token = token.substring(7);

        String username = jwtService.extractUsername(token);

        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));

//        If necessary then add more details.
        return UserResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
