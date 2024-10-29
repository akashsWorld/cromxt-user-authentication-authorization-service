package com.cromxt.user.service;


import com.cromxt.user.dtos.UserDetailDTO;
import com.cromxt.user.entity.UserEntity;


public interface DTOService {
    UserEntity getUserEntity(UserDetailDTO registerUser);
}
