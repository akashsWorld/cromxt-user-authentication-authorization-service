package com.cromxt.user.controller;

import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UserCredential;
import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;
import com.cromxt.user.service.AuthenticationService;
import com.cromxt.user.service.UserEntityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController{

    private final UserEntityService userEntityService;
    private final AuthenticationService authenticationService;
    private final Environment environment;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Validated RegisterUserDTO userDetail) {
        userEntityService.saveUser(userDetail);
    }
    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> generateToken(
            @RequestBody UserCredential userCredential,
            HttpServletResponse httpServletResponse) throws ServletException, IOException {
       var token = authenticationService.authenticate(userCredential);
       Cookie cookie = AuthenticationService.generateCookie(
               token.get("refreshToken"),
               Boolean.parseBoolean(environment.getProperty("USER_SERVICE.IS_SECURE")));

       httpServletResponse.addCookie(cookie);
       return ResponseEntity.ok(new AuthenticationResponseDTO(token.get("accessToken")));
    }

}
