package com.cromxt.user.service;

public interface UtilService {
    String generateUrlForAvatar(String email, String profileImageName);
    Boolean isValidApiKey(String apiKey);
}
