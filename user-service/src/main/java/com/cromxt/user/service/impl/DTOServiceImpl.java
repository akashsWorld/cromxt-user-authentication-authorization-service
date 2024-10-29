package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.requests.PhoneNumberDTO;
import com.cromxt.user.dtos.requests.RecoveryAccountDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;
import com.cromxt.user.entity.Gender;
import com.cromxt.user.entity.RecoveryAccountDetails;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.exceptions.InvalidRecoveryDetailsException;
import com.cromxt.user.service.DTOService;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class DTOServiceImpl implements DTOService {

    @Override
    public UserEntity getUserEntity(RegisterUserDTO registerUser) {
        return UserEntity.builder()
                .username(registerUser.email())
                .password(registerUser.password())
                .firstName(registerUser.firstName())
                .lastName(registerUser.lastName())
                .gender(registerUser.gender().equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE)
                .birthdate(Date.valueOf(registerUser.birthDate()))
                .build();
    }

    @Override
    public void updateUser(UserEntity userEntity, UpdateUserDTO updateUserDTO) {

        String value = updateUserDTO.firstName();

        if(value != null && !value.isEmpty()){
            userEntity.setFirstName(updateUserDTO.firstName());
        }

        value = updateUserDTO.lastName();
        if(value != null && !value.isEmpty()){
            userEntity.setLastName(updateUserDTO.lastName());
        }

        value = updateUserDTO.gender();
        if(value != null && !value.isEmpty()){
            userEntity.setGender(updateUserDTO.gender().equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE);
        }

        value = updateUserDTO.birthDate();
        if(value != null && !value.isEmpty()){
            userEntity.setBirthdate(Date.valueOf(updateUserDTO.birthDate()));
        }
    }

    @Override
    public RecoveryAccountDetails getRecoveryAccountDetails(RecoveryAccountDetailsDTO recoveryAccountDetailsDTO) {

        if(recoveryAccountDetailsDTO == null ||
                (recoveryAccountDetailsDTO.recoveryEmail() == null &&
                        recoveryAccountDetailsDTO.phoneNumberDTO() == null)){
            throw new InvalidRecoveryDetailsException("Invalid recovery details");
        }

        PhoneNumberDTO phoneNumberDTO = recoveryAccountDetailsDTO.phoneNumberDTO();

        return RecoveryAccountDetails.builder()
                .country(phoneNumberDTO.countryCode())
                .phoneNumber(phoneNumberDTO.phoneNumber())
                .recoveryEmail(recoveryAccountDetailsDTO.recoveryEmail())
                .build();
    }

}
