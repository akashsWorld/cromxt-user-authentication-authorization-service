package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.UserDetailDTO;
import com.cromxt.user.entity.Gender;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.service.DTOService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;

@Service
public class DTOServiceImpl implements DTOService {

    @Override
    public UserEntity getUserEntity(UserDetailDTO registerUser) {
        return UserEntity.builder()
                .username(registerUser.email())
                .password(registerUser.password())
                .firstName(registerUser.firstName())
                .lastName(registerUser.lastName())
                .gender(registerUser.gender().equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE)
                .birthdate(Date.valueOf(registerUser.birthDate()))
                .build();
    }
}
