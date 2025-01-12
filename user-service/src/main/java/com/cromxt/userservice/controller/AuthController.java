package com.cromxt.userservice.controller;


import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.dtos.requests.UserCredentialDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute NewUserRequest newUser) {
        return "redirect:/accounts/login";
    }

    @PostMapping("/authenticate")
    public String authenticate(
            @ModelAttribute UserCredentialDTO userCredentialDTO,
            @RequestParam(required = false) String redirectTo,
            @NonNull HttpServletResponse response) {
        System.out.println(userCredentialDTO.username()+" "+userCredentialDTO.password());
//        TODO: After authentication, add the token to the Cookie and redirect to the original page
        return "redirect:"+redirectTo;
    }
}
