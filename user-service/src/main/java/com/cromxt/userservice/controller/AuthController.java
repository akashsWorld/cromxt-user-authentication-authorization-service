package com.cromxt.userservice.controller;


import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.dtos.requests.UserCredentialDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @GetMapping(value = {"/sign-in","/", ""}, produces = "text/html")
    public String signIn(
            @RequestParam(name = "continueTo", required = false) String continueTo,
            Model model) {
        String baseUrl = System.getProperty("USER_SERVICE_BASE_URL");
        model.addAttribute("redirectUrl", continueTo);
        model.addAttribute("baseUrl",baseUrl);
        return "sign-in";
    }
    @PostMapping(value = "/login")
    public String authenticate(
            @ModelAttribute UserCredentialDTO userCredentialDTO,
            @RequestParam(required = false) String redirectTo,
            @NonNull HttpServletResponse response) {
        System.out.println(userCredentialDTO.username()+" "+userCredentialDTO.password());
//        TODO: After authentication, add the token to the Cookie and redirect to the original page
        return "redirect:"+redirectTo;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute NewUserRequest newUser) {
        return "redirect:/auth";
    }

    @GetMapping("/find-account")
    public String findAccount() {
        return "find-account";
    }
}
