package com.cromxt.user.controller;


import com.cromxt.user.dtos.requests.PasswordDetailsDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;
import com.cromxt.user.dtos.requests.UsernameDetailDTO;
import com.cromxt.user.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value = "/users")
public record UserController(
        UserEntityService userEntityService
) {

    @PutMapping(value = "/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @PathVariable String username, @RequestBody @Validated UpdateUserDTO updateUserDTO) {
        userEntityService.updateUser(username,updateUserDTO);
    }

    @PatchMapping("/username/{username}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUsername(@PathVariable String username, @RequestBody UsernameDetailDTO usernameDetailDTO) {
        userEntityService.updateEmail(username,usernameDetailDTO);
    }

    @PutMapping("/password/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable String username, @RequestBody PasswordDetailsDTO passwordDetails) {
        userEntityService.updatePassword(username,passwordDetails);
    }

    @PutMapping(value = "/image/{username}", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateImage(@PathVariable String username, @RequestParam(name = "profileImage") MultipartFile profileImage) {
        userEntityService.updateProfileImage(username,profileImage);
    }

    @DeleteMapping(value = "/image/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfileAvatar(@PathVariable String username) {
        userEntityService.deleteProfileAvatar(username);
    }
}
