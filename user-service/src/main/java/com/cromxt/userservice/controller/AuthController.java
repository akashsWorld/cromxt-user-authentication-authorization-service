package com.cromxt.userservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cromxt.userservice.dtos.others.Pair;
import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.dtos.requests.UserCredentialDTO;
import com.cromxt.userservice.entity.Gender;
import com.cromxt.userservice.service.CromUserService;
import com.cromxt.userservice.service.UtilService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    private final String BASE_URL;
    private final String HOME_URL;
    private final CromUserService cromUserService;
    private final UtilService utilService;

    public AuthController(
            CromUserService cromUserService,
            Environment environment,
            UtilService utilService) {
        this.cromUserService = cromUserService;
        String contextPath = environment.getProperty("server.servlet.context-path", String.class);
        if (contextPath == null) {
            BASE_URL = "";
        } else {
            BASE_URL = String.format("/%s", contextPath);
        }
        HOME_URL = environment.getProperty("USER_CONFIG_CROMXT_HOME", String.class);
        this.utilService = utilService;
    }

    @GetMapping(value = { "/sign-in", "/", "" }, produces = "text/html")
    public String signIn(
            @RequestParam(required = false) String continueTo,
            @RequestParam(required = false) String username,
            Model model) {
        continueTo = continueTo == null ? HOME_URL : continueTo;

        model.addAttribute("continueTo", continueTo);
        model.addAttribute("baseUrl", BASE_URL);
        return "sign-in";
    }

    @PostMapping(value = "/sign-in", produces = "text/html")
    public String authenticate(
            @ModelAttribute UserCredentialDTO userCredentialDTO,
            @RequestParam(required = false) String continueTo,
            @NonNull HttpServletResponse response) {
        // Authenticate user and generate tokens.
        Map<String, Pair<String, Boolean>> cookies = cromUserService.authenticate(userCredentialDTO);
        // Add the tokens to the cookie
        utilService.addCookies(response, cookies);

        continueTo = continueTo == null ? HOME_URL : continueTo;
        return "redirect:" + continueTo;
    }

    @GetMapping("/register")
    public String register(
            @RequestParam(required = false) String continueTo,
            Model model) {
        continueTo = continueTo == null ? HOME_URL : continueTo;
        model.addAttribute("baseUrl", BASE_URL);
        model.addAttribute("continueTo", continueTo);
        List<String> genderList = Arrays.stream(Gender.values()).map(eachValue->eachValue.toString()).collect(Collectors.toList());
        model.addAttribute("genders", genderList);  
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(
            @ModelAttribute NewUserRequest newUser,
            @RequestParam(required = false) String continueTo,
            Model model) {
        continueTo = continueTo == null ? HOME_URL : continueTo;
        model.addAttribute("baseUrl", BASE_URL);
        model.addAttribute("continueTo", continueTo);  
        cromUserService.saveUser(newUser);
        return "redirect:/auth";
    }

    @GetMapping("/find-account")
    public String findAccount(Model model, @RequestParam(required = false) String continueTo) {
        continueTo = continueTo == null ? HOME_URL : continueTo;
        model.addAttribute("baseUrl", BASE_URL);
        model.addAttribute("continueTo", continueTo);
        return "find-account";
    }

    @PostMapping("/find-account")
    public String accountsByEmail(
            @RequestParam(name = "accountEmail") String email,
            Model model) {
        model.addAttribute("baseUrl", BASE_URL);
        model.addAttribute("accounts", cromUserService.findUsersByEmail(email));
        return "account-list";
    }
}
