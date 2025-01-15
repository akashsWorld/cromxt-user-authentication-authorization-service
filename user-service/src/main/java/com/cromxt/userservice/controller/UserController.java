package com.cromxt.userservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"/accounts","/"})
public class UserController {



    @GetMapping(value = {"/login","/"})
    public String login(
            @RequestParam(name = "continueTo", required = false) String continueTo,
            Model model) {
        String baseUrl = System.getProperty("USER_SERVICE_BASE_URL");
        model.addAttribute("redirectUrl", continueTo);
        model.addAttribute("baseUrl",baseUrl);
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
