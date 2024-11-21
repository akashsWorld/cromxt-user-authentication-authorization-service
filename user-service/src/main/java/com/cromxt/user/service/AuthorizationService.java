package com.cromxt.user.service;

import com.cromxt.user.dtos.requests.UserCredential;
import com.cromxt.user.dtos.responses.UserResponse;

public interface AuthorizationService {
    UserResponse getUserDetails(String username);
}
