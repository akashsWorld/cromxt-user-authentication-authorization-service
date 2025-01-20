package com.cromxt.userservice.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cromxt.userservice.dtos.others.Pair;

import jakarta.servlet.http.HttpServletResponse;

public interface UtilService {

    String uploadFile(MultipartFile file);
    void addCookies(HttpServletResponse response, Map<String,Pair<String,Boolean>> cookies);
}
