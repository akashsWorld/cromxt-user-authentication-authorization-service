package com.cromxt.userservice.controller;


import com.cromxt.userservice.dtos.requests.NewUserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/accounts","/"})
public class UserController {

    @GetMapping(value = {"/login","/"})
    public String login(@RequestParam(name = "continueTo", required = false) String continueTo) {
        System.out.println(continueTo);
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
