package com.cromxt.userservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface UtilService {

    String uploadFile(MultipartFile file);
}
