package com.cromxt.user.service;


import com.cromxt.user.dtos.requests.RecoveryAccountDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;
import com.cromxt.user.entity.RecoveryAccountDetails;
import com.cromxt.user.entity.UserEntity;


public interface DTOService {
    UserEntity getUserEntity(RegisterUserDTO registerUser);

    void updateUser(UserEntity userEntity, UpdateUserDTO updateUserDTO);
    RecoveryAccountDetails getRecoveryAccountDetails(RecoveryAccountDetailsDTO recoveryAccountDetailsDTO);
}
