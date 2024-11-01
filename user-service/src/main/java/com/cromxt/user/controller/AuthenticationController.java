package com.cromxt.user.controller;

import com.cromxt.user.dtos.requests.LoginUserDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.service.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/cromxt/auth")
public record AuthenticationController(
        UserDetailService userDetailService
) {

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Validated RegisterUserDTO userDetail) {
        userDetailService.saveUser(userDetail);
    }

}
