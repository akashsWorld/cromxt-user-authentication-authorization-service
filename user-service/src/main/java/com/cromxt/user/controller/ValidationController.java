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
@RequestMapping(value = "/validate")
@RequiredArgsConstructor
public class ValidationController{

    private final AuthorizationService authorizationService;
    @Value("${USER_SERVICE.API_KEY}")
    private String API_KEY;


    @PostMapping
    public ResponseEntity<UserResponse> getUserDetails(@RequestBody UserCredential credential ,
                                                       @RequestHeader(value = "X-API-KEY") String key) {
        if(key == null || !key.equals(API_KEY)) {
            throw new UnauthorizedAPIKeyException("Unauthorized API Key");
        }
        return ResponseEntity.ok(authorizationService.getUserDetails(credential));
    }
}
