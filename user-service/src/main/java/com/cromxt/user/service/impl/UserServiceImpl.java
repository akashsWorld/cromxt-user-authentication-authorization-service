package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.UserDetailDTO;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.repository.UserEntityRepository;
import com.cromxt.user.service.DTOService;
import com.cromxt.user.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserEntityService {

    private final DTOService dtoService;
    private final UserEntityRepository userEntityRepository;
    private final DateFormatter dateFormatter;

    @Override
    public void saveUser(UserDetailDTO registerUser) {
        String email = registerUser.email();

        Boolean exists = userEntityRepository.existsByUsername(email);
        if(exists)
            throw new RuntimeException("User already exists.");
        UserEntity user = dtoService.getUserEntity(registerUser);
        userEntityRepository.save(user);
    }

    @Override
    public void updateUser(UserDetailDTO userDetail) {

    }


}
