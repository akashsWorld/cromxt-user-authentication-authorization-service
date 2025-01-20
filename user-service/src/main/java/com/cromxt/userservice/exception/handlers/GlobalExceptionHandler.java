package com.cromxt.userservice.exception.handlers;

import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final String BASE_URL;

    public GlobalExceptionHandler(Environment environment) {
        String contextPath = environment.getProperty("server.servlet.context-path", String.class);
        if (contextPath==null) {
            BASE_URL="";
        }else{
            BASE_URL = String.format("/%s", contextPath);
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, 
    Exception e) {

        model.addAttribute("baseUrl", BASE_URL);

        return "error";
    }
}
