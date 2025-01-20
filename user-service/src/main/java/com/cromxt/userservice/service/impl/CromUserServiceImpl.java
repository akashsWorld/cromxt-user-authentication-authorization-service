package com.cromxt.userservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cromxt.jwt.JWTService;
import com.cromxt.userservice.dtos.others.Pair;
import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.dtos.requests.UserCredentialDTO;
import com.cromxt.userservice.dtos.response.UserAccountResponse;
import com.cromxt.userservice.entity.CromUser;
import com.cromxt.userservice.exception.InvalidUserCredentialsException;
import com.cromxt.userservice.repository.CromUserRepository;
import com.cromxt.userservice.service.CromUserService;
import com.cromxt.userservice.service.DTOEntityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CromUserServiceImpl implements CromUserService{

    private final CromUserRepository cromUserRepository;
    private final DTOEntityMapper dtoEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    @Override
    public List<UserAccountResponse> findUsersByEmail(String email) {
        // return List.of(
        //     new UserAccountResponse("user1", "https://images.unsplash.com/photo-1535579710123-3c0f261c474e?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        //     new UserAccountResponse("user2", "https://images.unsplash.com/photo-1531287333398-6d7bd77ef790?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
        // );
        return List.of();
    }

    @Override
    public void saveUser(NewUserRequest newUser) {
        CromUser newCromUser = dtoEntityMapper.getCromUser(newUser);
        cromUserRepository.save(newCromUser);
    }

    @Override
    public Map<String, Pair<String, Boolean>> authenticate(UserCredentialDTO userCredentialDTO) {
        String username = userCredentialDTO.username();
        String password = userCredentialDTO.password();
        Optional<CromUser> cromUser = cromUserRepository.findByUsername(username);

        CromUser savedUser = cromUser.orElseThrow(()-> new InvalidUserCredentialsException("User not found"));

        boolean isValidPassword = passwordEncoder.matches(password, savedUser.getPassword());

        if(!isValidPassword){
            throw new InvalidUserCredentialsException("Invalid user credentials");
        }
        Map<String, Pair<String,Boolean>> cookies = new HashMap<>();

        String accessToken = jwtService.generateAccessToken(savedUser, new HashMap<>());
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        
        cookies.put("Refresh-Token", new Pair<>(refreshToken, true));
        cookies.put("Authorization", new Pair<>(accessToken, true));
        return cookies;
    }

}
