package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.requests.EmailDetailDTO;
import com.cromxt.user.dtos.requests.PasswordDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;
import com.cromxt.user.entity.RecoveryAccountDetails;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.exceptions.UserAlreadyExistsException;
import com.cromxt.user.exceptions.UserNotFoundException;
import com.cromxt.user.repository.RecoveryAccountDetailsRepository;
import com.cromxt.user.repository.UserEntityRepository;
import com.cromxt.user.service.DTOService;
import com.cromxt.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailService{

    private final DTOService dtoService;
    private final UserEntityRepository userEntityRepository;
    private final RecoveryAccountDetailsRepository recoveryAccountDetailsRepository;
    private final DateFormatter dateFormatter;

    @Override
    @Transactional
    public void saveUser(RegisterUserDTO registerUser) {
        String email = registerUser.email();
        boolean exists = userEntityRepository.existsByUsername(email);
        if(exists)
            throw new UserAlreadyExistsException("Already exists a user with email: " + email);
        UserEntity user = dtoService.getUserEntity(registerUser);
        RecoveryAccountDetails recoveryAccountDetails = dtoService.getRecoveryAccountDetails(registerUser.recoveryAccountDetailsDTO());
        userEntityRepository.save(user);
        recoveryAccountDetails.setUser(user);
        recoveryAccountDetailsRepository.save(recoveryAccountDetails);
    }

    @Override
    public void updateUser(String email, UpdateUserDTO updateUserDTO) {
        UserEntity userEntity = userEntityRepository.findByUsername(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));
        dtoService.updateUser(userEntity,updateUserDTO);
        userEntityRepository.save(userEntity);
    }

    @Override
    public void updateEmail(String email, EmailDetailDTO emailDetailDTO) {
        UserEntity userEntity = userEntityRepository.findByUsername(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));
        userEntity.setUsername(emailDetailDTO.newEmail());
        userEntityRepository.save(userEntity);
    }

    @Override
    public void updatePassword(String email, PasswordDetailsDTO passwordDetails) {
//            TODO: Mail a link into mailbox to update password through a html page.
    }


}
