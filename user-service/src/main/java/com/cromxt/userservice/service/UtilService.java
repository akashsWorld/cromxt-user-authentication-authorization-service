package com.cromxt.userservice.service;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

public interface UtilService {

    String uploadFile(MultipartFile file);

    void addRefreshTokenCookie(HttpServletResponse response,String refreshToken, String refreshTokenEndpointPath);
}
