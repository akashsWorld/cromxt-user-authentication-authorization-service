package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.requests.PhoneNumberDTO;
import com.cromxt.user.dtos.requests.RecoveryAccountDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;
import com.cromxt.user.entity.*;
import com.cromxt.user.exceptions.InvalidRecoveryDetailsException;
import com.cromxt.user.service.DTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DTOServiceImpl implements DTOService {
    private final PasswordEncoder passwordEncoder;

    private static final Map<String, Gender> ALLOWED_GENDERS = Arrays.stream(Gender.values()).collect(Collectors.toMap(
            gender -> gender.getGender().toUpperCase(),gender-> Gender.valueOf(gender.name())
    ));
    private static final Map<String, CountryCode> ALLOWED_COUNTRY_CODE = Arrays.stream(CountryCode.values()).collect(Collectors.toMap(
            CountryCode::getCode,country->CountryCode.valueOf(country.name())
    ));

    @Override
    public UserEntity getUserEntity(RegisterUserDTO registerUser) {
        return UserEntity.builder()
                .username(registerUser.username())
                .password(passwordEncoder.encode(registerUser.password()))
                .firstName(registerUser.firstName())
                .lastName(registerUser.lastName())
                .role(Role.USER)
                .gender(ALLOWED_GENDERS.get(registerUser.gender().toUpperCase()))
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
            if(!ALLOWED_GENDERS.containsKey(value.toUpperCase())){
                throw new IllegalArgumentException("Invalid gender");
            }
            userEntity.setGender(ALLOWED_GENDERS.get(value));
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
                        recoveryAccountDetailsDTO.phoneNumber() == null)){
            throw new InvalidRecoveryDetailsException("Invalid recovery details");
        }

        final RecoveryAccountDetails recoveryAccountDetails = RecoveryAccountDetails.builder().build() ;

        PhoneNumberDTO phoneNumberDTO = recoveryAccountDetailsDTO.phoneNumber();

        if(phoneNumberDTO != null){
            recoveryAccountDetails.setCountry(ALLOWED_COUNTRY_CODE.get(phoneNumberDTO.countryCode()));
            recoveryAccountDetails.setPhoneNumber(phoneNumberDTO.phoneNumber());
        }
        if(recoveryAccountDetailsDTO.recoveryEmail() != null){
            recoveryAccountDetails.setRecoveryEmail(recoveryAccountDetailsDTO.recoveryEmail());
        }

        return recoveryAccountDetails;
    }

    @Override
    public ProfileAvatar getProfileAvatar(MultipartFile profileImage, String url) {
        return ProfileAvatar.builder()
                .url(url)
                .format(profileImage.getContentType())
                .imgSize(String.valueOf(profileImage.getSize()))
                .build();
    }

    @Override
    public void recoveryAccountDetails(RecoveryAccountDetails recoveryAccountDetails, RecoveryAccountDetailsDTO recoveryAccountDetailsDTO) {
        if(recoveryAccountDetailsDTO.recoveryEmail() != null){
            recoveryAccountDetails.setRecoveryEmail(recoveryAccountDetailsDTO.recoveryEmail());
        }else {
            recoveryAccountDetails.setRecoveryEmail(null);
        }
        if(recoveryAccountDetailsDTO.phoneNumber() != null){
            recoveryAccountDetails.setCountry(ALLOWED_COUNTRY_CODE.get(recoveryAccountDetailsDTO.phoneNumber().countryCode()));
            recoveryAccountDetails.setPhoneNumber(recoveryAccountDetailsDTO.phoneNumber().phoneNumber());
        }else {
            recoveryAccountDetails.setCountry(null);
            recoveryAccountDetails.setPhoneNumber(null);
        }
    }

}
