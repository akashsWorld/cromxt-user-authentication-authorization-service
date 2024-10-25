package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.RegisterUser;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.service.DTOService;
import com.cromxt.user.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserEntityService {

    private final DTOService dtoService;

    @Override
    public void saveUser(RegisterUser registerUser) {

    }


}
