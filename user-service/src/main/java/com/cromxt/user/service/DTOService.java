package com.cromxt.user.service;


import com.cromxt.user.dtos.RegisterUser;
import com.cromxt.user.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface DTOService {
    UserEntity getUserEntity(RegisterUser registerUser);
}
