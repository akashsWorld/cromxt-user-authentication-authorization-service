package com.cromxt.user.controller;

import com.cromxt.user.dtos.requests.LoginUserDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;
import com.cromxt.user.service.AuthenticationService;
import com.cromxt.user.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public record AuthenticationController(
        UserEntityService userEntityService,
        AuthenticationService authenticationService
) {

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Validated RegisterUserDTO userDetail) {
        userEntityService.saveUser(userDetail);
    }
    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> generateToken(@RequestBody LoginUserDTO loginUserDTO){
        return ResponseEntity.ok().body(authenticationService.authenticate(loginUserDTO));
    }

}
