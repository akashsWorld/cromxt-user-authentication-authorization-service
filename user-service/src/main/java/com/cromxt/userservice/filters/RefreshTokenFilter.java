package com.cromxt.userservice.filters;

import java.io.IOException;
import java.util.Arrays;

import com.cromxt.jwt.JWTService;
import com.cromxt.userservice.exception.UnauthorizedAccessException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RefreshTokenFilter implements Filter {
    private final JWTService jwtService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
                System.out.println("RefreshTokenFilter hitted...");
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        try {
            
            Cookie[] cookies = servletRequest.getCookies();
            if (cookies == null) {
                throw new UnauthorizedAccessException("No cookies found");
            }
            Cookie tokenCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("Refresh-Token"))
                    .findFirst().orElseThrow(()-> new UnauthorizedAccessException("No refresh token found"));
            
            String refreshToken = tokenCookie.getValue();

            System.out.println(refreshToken);

            if(refreshToken == null){
                throw new UnauthorizedAccessException("No refresh token found");
            }
            
            String username = jwtService.extractUsername(refreshToken);
            
            servletRequest.setAttribute("username",username);
            chain.doFilter(servletRequest, response);
            return;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            response.getWriter().write("Error Occured...");
        }

    }

}
