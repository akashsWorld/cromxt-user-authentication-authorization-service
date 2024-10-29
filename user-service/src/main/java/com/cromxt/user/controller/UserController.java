package com.cromxt.user.controller;


import com.cromxt.user.dtos.requests.EmailDetailDTO;
import com.cromxt.user.dtos.requests.PasswordDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;
import com.cromxt.user.service.UserDetailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/cromxt/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailService userDetailService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid RegisterUserDTO userDetail) {
        userDetailService.saveUser(userDetail);
    }

    @PutMapping(value = "/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable @Valid @Email String email, @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        userDetailService.updateUser(email,updateUserDTO);
    }

    @PatchMapping("/email/{email}")
    @ResponseStatus(value =HttpStatus.NO_CONTENT)
    public void updateEmail(@PathVariable String email, @RequestBody EmailDetailDTO newEmail) {
        userDetailService.updateEmail(email,newEmail);
    }

    @PatchMapping("/password/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable String email, @RequestBody PasswordDetailsDTO passwordDetails) {
        userDetailService.updatePassword(email,passwordDetails);
    }


}
