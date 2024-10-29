package com.cromxt.user.controller;


import com.cromxt.user.dtos.UserDetailDTO;
import com.cromxt.user.service.UserEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/cromxt/users/accounts")
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid UserDetailDTO userDetail) {
        userEntityService.saveUser(userDetail);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDetailDTO userDetail) {
        userEntityService.updateUser(userDetail);
    }

}
