package com.cromxt.user.service;

import com.cromxt.cromspace.dtos.UserResponse;
import com.cromxt.cromspace.dtos.UserValidationDTO;

public interface AuthorizationService {
    Boolean isRequestValid(UserValidationDTO userValidationDTO);
    UserResponse getUserDetails(String token);
}
