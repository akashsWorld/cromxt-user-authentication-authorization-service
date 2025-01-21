package com.cromxt.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.userservice.constants.ApplicationConstants;
import com.cromxt.userservice.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final AuthService authService;

    @RequestMapping(value = ApplicationConstants.REFRESH_TOKEN_ENDPOINT, method = {RequestMethod.GET})
    public String generateAccessToken(
            @RequestParam(required = false) String refreshToken,
            @RequestParam(required = false) String continueTo,
            @NonNull HttpServletResponse response) {

        // String token = cromUserService.gererateAccessToken(refreshToken);
        
        String token = "token";

        response.addHeader("Authorization", String.format("Bearer %s", token));
        return "redirect:" + continueTo;
    }
}
