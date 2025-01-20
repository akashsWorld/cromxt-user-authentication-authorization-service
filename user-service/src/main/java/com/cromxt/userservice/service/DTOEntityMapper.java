package com.cromxt.userservice.service;

import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.entity.CromUser;

public interface DTOEntityMapper {
    CromUser getCromUser(NewUserRequest newUser);
}
