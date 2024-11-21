package com.cromxt.user.filter;


import com.cromxt.user.dtos.responses.ErrorResponse;
import com.cromxt.user.exceptions.UnauthorizedAPIKeyException;
import com.cromxt.user.exceptions.UnauthorizedAccessEndpointException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;


public class APIKeyAuthorizationFilter implements Filter {

    private final String API_KEY;
    private final static String USER_URL = "/service";

    public APIKeyAuthorizationFilter(String apiKey) {
        this.API_KEY=apiKey;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI().substring(7);
        if(!requestURI.startsWith(USER_URL)) {
            chain.doFilter(request, servletResponse);
            return;
        }
        String apiKey = request.getHeader("X-API-KEY");
        if (apiKey == null || !apiKey.equals(API_KEY)) {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(objectMapper.writeValueAsString(new ErrorResponse("Invalid API key!", UnauthorizedAPIKeyException.class.getName())));
            return;
        }
        chain.doFilter(request, servletResponse);
    }
}
