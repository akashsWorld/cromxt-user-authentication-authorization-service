package com.cromxt.userservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cromxt.userservice.constants.ApplicationConstants;
import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.dtos.requests.UserCredentialDTO;
import com.cromxt.userservice.entity.Gender;
import com.cromxt.userservice.service.AuthService;
import com.cromxt.userservice.service.CromUserService;
import com.cromxt.userservice.service.UtilService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CromUserService cromUserService;
    private final UtilService utilService;
    private final AuthService authService;
    private final ApplicationConstants applicationConstants;

    @GetMapping(value = { "/sign-in", "/", "" }, produces = "text/html")
    public String signIn(
            @RequestParam(required = false) String continueTo,
            @RequestParam(required = false) String username,
            Model model) {
        continueTo = continueTo == null ? applicationConstants.getHomeUrl() : continueTo;

        model.addAttribute("continueTo", continueTo);
        model.addAttribute("baseUrl", applicationConstants.getBaseUrl());
        return "sign-in";
    }

    @PostMapping(value = "/sign-in")
    public String authenticate(
            @ModelAttribute UserCredentialDTO userCredentialDTO,
            @RequestParam(required = false) String continueTo,
            @NonNull HttpServletResponse response) {
        // Authenticate user and generate tokens.
        Map<String, String> tokens = authService.authenticate(userCredentialDTO);
        String refreshToken = tokens.get("Refresh-Token");
        String accessToken = tokens.get("Authorization");
        // Add the tokens to the cookie
        System.out.println(refreshToken);
        utilService.addRefreshTokenCookie(response, refreshToken,
                ApplicationConstants.REFRESH_TOKEN_ENDPOINT);

        response.addHeader("Refresh-Token", refreshToken);
        response.addHeader("Authorization", String.format("Bearer %s", accessToken));
        continueTo = continueTo == null ? applicationConstants.getHomeUrl() : continueTo;
        return "redirect:" + continueTo;
    }

    @GetMapping("/register")
    public String register(
            @RequestParam(required = false) String continueTo,
            Model model) {
        continueTo = continueTo == null ? applicationConstants.getHomeUrl() : continueTo;
        model.addAttribute("baseUrl", applicationConstants.getBaseUrl());
        model.addAttribute("continueTo", continueTo);
        List<String> genderList = Arrays.stream(Gender.values()).map(eachValue -> eachValue.toString())
                .collect(Collectors.toList());
        model.addAttribute("genders", genderList);
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(
            @ModelAttribute NewUserRequest newUser,
            @RequestParam(required = false) String continueTo,
            Model model) {
        continueTo = continueTo == null ? applicationConstants.getHomeUrl() : continueTo;
        model.addAttribute("baseUrl", applicationConstants.getBaseUrl());
        model.addAttribute("continueTo", continueTo);
        cromUserService.saveUser(newUser);
        return "redirect:/auth";
    }

    @GetMapping("/find-account")
    public String findAccount(Model model, @RequestParam(required = false) String continueTo) {
        continueTo = continueTo == null ? applicationConstants.getHomeUrl() : continueTo;
        model.addAttribute("baseUrl", applicationConstants.getBaseUrl());
        model.addAttribute("continueTo", continueTo);
        return "find-account";
    }

    @PostMapping("/find-account")
    public String accountsByEmail(
            @RequestParam(name = "accountEmail") String email,
            Model model) {
        model.addAttribute("accounts", cromUserService.findUsersByEmail(email));
        return "account-list";
    }

    @PostMapping("/logout")
    public String generateRefreshToken(HttpServletResponse response) {

        utilService.addRefreshTokenCookie(response, "", ApplicationConstants.REFRESH_TOKEN_ENDPOINT);

        return "redirect:/auth";
    }
}
