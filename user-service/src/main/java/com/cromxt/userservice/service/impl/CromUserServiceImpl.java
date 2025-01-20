package com.cromxt.userservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.dtos.response.UserAccountResponse;
import com.cromxt.userservice.service.CromUserService;

@Service
public class CromUserServiceImpl implements CromUserService{

    @Override
    public List<UserAccountResponse> findUsersByEmail(String email) {
        // return List.of(
        //     new UserAccountResponse("user1", "https://images.unsplash.com/photo-1535579710123-3c0f261c474e?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        //     new UserAccountResponse("user2", "https://images.unsplash.com/photo-1531287333398-6d7bd77ef790?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
        // );
        return List.of();
    }

    @Override
    public void saveUser(NewUserRequest newUser) {
        
    }

}
