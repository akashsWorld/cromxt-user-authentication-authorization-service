package com.cromxt.userservice.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cromxt.userservice.dtos.others.Pair;
import com.cromxt.userservice.service.UtilService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UtilServiceImpl implements UtilService {

    @Override
    public String uploadFile(MultipartFile file) {
        // TODO : Implement this method
        

        return "";
    }

    @Override
    public void addCookies(HttpServletResponse response, Map<String, Pair<String, Boolean>> cookies) {
        cookies.keySet().forEach((cookieName)->{
            Cookie newCookie = new Cookie(cookieName, cookies.get(cookieName).getData1());
            newCookie.setHttpOnly(cookies.get(cookieName).getData2());
            response.addCookie(newCookie);
        });
    }

}
