package com.cromxt.user.controller;


import com.cromxt.cromspace.dtos.UserResponse;
import com.cromxt.cromspace.dtos.UserValidationDTO;
import com.cromxt.user.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/validate")
@RequiredArgsConstructor
public class ValidationController{

        private final AuthorizationService authorizationService;
        private static final String API_KEY = "cromspace";



    @PostMapping
    public ResponseEntity<Boolean> isUserValid(@RequestBody UserValidationDTO userValidationDTO,
                                               @RequestHeader(value = "X-API-KEY") String key) {
        if(key == null || !key.equals(API_KEY)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorizationService.isRequestValid(userValidationDTO));
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserDetails(@RequestHeader(value = "Authorization") String token ,
                                                       @RequestHeader(value = "X-API-KEY") String key) {
        if(key == null || !key.equals(API_KEY)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(authorizationService.getUserDetails(token));
    }
}
