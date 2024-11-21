package com.cromxt.user.service;

import com.cromxt.user.dtos.requests.UserCredential;
import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;
import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthenticationService {
    Map<String,String> authenticate(UserCredential request);
    Map<String,String> generateRefreshToken(String username);
    String generateAccessToken(String token);
    static Cookie generateCookie(String token,Boolean isSecure) {
        Cookie cookie = new Cookie("refreshToken",token);
        cookie.setHttpOnly(true);
//       TODO:Change it Later.
        cookie.setSecure(isSecure);
        cookie.setPath("/cromxt/tokens/refresh");
        cookie.setMaxAge(3600);
        return cookie;
    }
}
