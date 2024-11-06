package com.cromxt.user.service.impl;

import com.cromxt.user.service.StorageService;
import com.cromxt.user.service.UtilService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UtilServiceImpl implements UtilService, StorageService {
    @Value("${USER_SERVICE.API_KEY}")
    private String API_KEY;

    @Override
    public void saveProfileAvatar(MultipartFile file, String url) {
//        Before save handle the no profileAvatar found case to handle firstTimeSaving.
//    TODO:implement
    }

    @Override
    public void deleteProfileAvatar(String url) {
//    TODO: implement
    }

    @Override
    public String generateUrlForAvatar(String userId, String profileImageName) {
        return String.format("%s##%s",userId,profileImageName);
    }

    @Override
    public Boolean isValidApiKey(String apiKey) {
        return API_KEY.equals(apiKey);
    }

}
