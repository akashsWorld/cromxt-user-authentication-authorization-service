package com.cromxt.user.controller;


import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;
import com.cromxt.user.service.AuthenticationService;
import com.cromxt.user.service.JWTService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/tokens")
public record TokenController(
        JWTService jwtService,
        AuthenticationService authenticationService
) {
    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> refreshAccessToken(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String token = request.getHeader("refreshToken");
        return ResponseEntity.ok(new AuthenticationResponseDTO(authenticationService.generateAccessToken(token)));
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<AuthenticationResponseDTO> renewRefreshToken(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String token = request.getHeader("refreshToken");
        Map<String,String>  tokens = authenticationService.generateRefreshToken(jwtService.extractUsername(token));
        Cookie cookie = AuthenticationService.generateCookie(tokens.get("refreshToken"));
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new AuthenticationResponseDTO(tokens.get("accessToken")));
    }

}
