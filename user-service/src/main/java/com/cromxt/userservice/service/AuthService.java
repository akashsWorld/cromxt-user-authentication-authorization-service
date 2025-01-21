package com.cromxt.userservice.service;

import java.util.Map;

import com.cromxt.userservice.dtos.requests.UserCredentialDTO;

public interface AuthService {

    Map<String,String> authenticate(UserCredentialDTO userCredentialDTO);

    String gererateAccessToken(String username);

}
