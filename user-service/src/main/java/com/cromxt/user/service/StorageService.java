package com.cromxt.user.service;

import org.springframework.web.multipart.MultipartFile;


public interface StorageService {
    void saveProfileAvatar(MultipartFile file, String url);
    void deleteProfileAvatar(String url);
}
