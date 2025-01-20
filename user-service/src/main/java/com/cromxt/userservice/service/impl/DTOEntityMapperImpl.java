package com.cromxt.userservice.service.impl;

import java.sql.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cromxt.userservice.dtos.requests.NewUserRequest;
import com.cromxt.userservice.entity.CromUser;
import com.cromxt.userservice.entity.Gender;
import com.cromxt.userservice.entity.Role;
import com.cromxt.userservice.service.DTOEntityMapper;
import com.cromxt.userservice.service.UtilService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DTOEntityMapperImpl implements DTOEntityMapper{

    private final PasswordEncoder passwordEncoder;
    private final UtilService utilService;
    @Override
    public CromUser getCromUser(NewUserRequest newUser) {
        String encodedPassword = passwordEncoder.encode(newUser.password());
        String avatar = utilService.uploadFile(newUser.avatar());
        log.info("The gender of the User ",newUser.gender());
        return CromUser.builder()
                .email(newUser.email())
                .username(newUser.username())
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .password(encodedPassword)
                .gender(Gender.MALE)
                .role(Role.USER)
                .dateOfBirth(Date.valueOf(newUser.dateOfBirth()))
                .avatar(avatar)
                .build();
    }
    
}
