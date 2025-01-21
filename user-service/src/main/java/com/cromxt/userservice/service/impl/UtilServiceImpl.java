package com.cromxt.userservice.service.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cromxt.userservice.service.UtilService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UtilServiceImpl implements UtilService {

    private String BASE_URL;

    public UtilServiceImpl(Environment environment) {
        String contextPath = environment.getProperty("server.servlet.context-path", String.class);
        if (BASE_URL == null) {
            BASE_URL = "";
        } else {
            BASE_URL = String.format("/%s", contextPath);
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        // TODO : Implement this method
        

        return "";
    }

    @Override
    public void addRefreshTokenCookie(
        HttpServletResponse response, 
        String refreshToken,
        String refreshTokenEndpointPath) {
            Cookie newCookie = new Cookie("Refresh-Token", refreshToken);
            newCookie.setHttpOnly(true);
            newCookie.setPath(String.format("%s%s", BASE_URL, refreshTokenEndpointPath));
            response.addCookie(newCookie);
    }

}
