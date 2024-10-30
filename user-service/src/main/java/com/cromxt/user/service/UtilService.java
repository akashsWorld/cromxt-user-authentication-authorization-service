package com.cromxt.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface UtilService {
    public String generateUrlForAvatar(String email, String profileImageName);
}
