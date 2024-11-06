package com.cromxt.user.service;

import com.cromxt.user.dtos.requests.LoginUserDTO;
import com.cromxt.user.dtos.responses.AuthenticationResponseDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO authenticate(LoginUserDTO request);
}
