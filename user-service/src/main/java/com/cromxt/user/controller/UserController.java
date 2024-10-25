package com.cromxt.user.controller;


import com.cromxt.user.dtos.RegisterUser;
import com.cromxt.user.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/register/cromxt/users")
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;

    @GetMapping(value = "/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "Hello World";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(RegisterUser registerUser){
        userEntityService.saveUser(registerUser);
    }
}
