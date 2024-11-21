package com.cromxt.user.filter;

import com.cromxt.user.exceptions.UnauthorizedAccessEndpointException;
import com.cromxt.user.service.JWTService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class AccessTokenAuthorizationFilter implements Filter {
    private final JWTService jwtService;
    private final static String USER_URL = "/users";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String requestURI = request.getRequestURI().substring(7);
        if (!requestURI.startsWith(USER_URL)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedAccessEndpointException("Invalid token!");
        }
        String username = requestURI.lastIndexOf('/') > 0 ? requestURI.substring(requestURI.lastIndexOf('/') + 1) : null;
        if(username == null){
            throw new UnauthorizedAccessEndpointException("Invalid token!");
        }
        if(!jwtService.isTokenValid(token.substring(7),username)){
            throw new IllegalStateException("Invalid token!");
        }
        filterChain.doFilter(request, response);
    }
}
