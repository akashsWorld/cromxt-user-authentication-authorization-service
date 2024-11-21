package com.cromxt.user.controller;


import com.cromxt.user.dtos.requests.UserCredential;
import com.cromxt.user.dtos.responses.UserResponse;
import com.cromxt.user.exceptions.UnauthorizedAPIKeyException;
import com.cromxt.user.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/service")
@RequiredArgsConstructor
public class ValidationController{

    private final AuthorizationService authorizationService;

    @PostMapping(value="{username}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable String username) throws UnauthorizedAPIKeyException {
        return ResponseEntity.ok(authorizationService.getUserDetails(username));
    }
}
