package com.cromxt.userservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.userservice.dtos.response.UserAccountResponse;
import com.cromxt.userservice.service.CromUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final CromUserService cromUserService;

    @GetMapping(value = "/{email}")
    public ResponseEntity<List<UserAccountResponse>> findUsersByEmail(@PathVariable String email) {
        return ResponseEntity.ok(cromUserService.findUsersByEmail(email));
    }
    
}
