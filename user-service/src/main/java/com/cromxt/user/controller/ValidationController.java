package com.cromxt.user.controller;


import com.cromxt.user.dtos.responses.UserResponse;
import com.cromxt.user.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service")
@RequiredArgsConstructor
public class ValidationController{

    private final AuthorizationService authorizationService;

    @GetMapping(value="{username}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable String username) {
        return ResponseEntity.ok(authorizationService.getUserDetails(username));
    }
}
