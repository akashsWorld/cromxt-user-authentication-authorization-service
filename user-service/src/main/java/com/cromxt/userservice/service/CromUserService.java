package com.cromxt.userservice.service;

import java.util.List;
import java.util.Map;

import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.dtos.requests.UserCredentialDTO;
import com.cromxt.userservice.dtos.response.UserAccountResponse;

public interface CromUserService {

    List<UserAccountResponse> findUsersByEmail(String email);

    void saveUser(NewUserRequest newUser);


}
