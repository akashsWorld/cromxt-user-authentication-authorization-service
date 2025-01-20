package com.cromxt.userservice.controller;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;


@Component
public class UserErrorController implements ErrorViewResolver {

    private final String BASE_URL;

    public UserErrorController(Environment environment) {
        BASE_URL = environment.getProperty("server.servlet.context-path", String.class);
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request,
                                         HttpStatus status,
                                         Map<String, Object> model) {
        model.put("baseUrl", BASE_URL);                         
        return new ModelAndView("redirect:/auth/sign-in", model);
    }
}
