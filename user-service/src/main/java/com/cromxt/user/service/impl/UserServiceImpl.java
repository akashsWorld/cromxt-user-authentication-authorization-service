package com.cromxt.user.service.impl;

import com.cromxt.user.dtos.requests.*;
import com.cromxt.user.entity.ProfileAvatar;
import com.cromxt.user.entity.RecoveryAccountDetails;
import com.cromxt.user.entity.UserEntity;
import com.cromxt.user.exceptions.*;
import com.cromxt.user.repository.ProfileAvatarRepository;
import com.cromxt.user.repository.RecoveryAccountDetailsRepository;
import com.cromxt.user.repository.UserEntityRepository;
import com.cromxt.user.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserEntityService {

    private static final List<String> ALLOWED_FILE_EXTENSIONS = Arrays.asList("image/png", "image/jpg", "image/jpeg");
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{3,16}$";
    private final DTOService dtoService;
    private final UserEntityRepository userEntityRepository;
    private final RecoveryAccountDetailsRepository recoveryAccountDetailsRepository;
    private final ProfileAvatarRepository profileAvatarRepository;
    private final StorageService storageService;
    private final UtilService utilService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveUser(RegisterUserDTO registerUser) {
        String username = registerUser.username();
        boolean exists = userEntityRepository.existsByUsername(username);
        if(exists)
            throw new UserAlreadyExistsException("Already exists a user with username: " + username);
        UserEntity user = dtoService.getUserEntity(registerUser);
        RecoveryAccountDetails recoveryAccountDetails = dtoService.getRecoveryAccountDetails(registerUser.recoveryAccountDetails());
        userEntityRepository.save(user);
        recoveryAccountDetails.setUser(user);
        recoveryAccountDetailsRepository.save(recoveryAccountDetails);
    }

    @Override
    @Transactional
    public void updateUser(String username, UpdateUserDTO updateUserDTO) {
        UserEntity userEntity = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
        dtoService.updateUser(userEntity,updateUserDTO);
        RecoveryAccountDetailsDTO recoveryAccountDetailsDTO = updateUserDTO.recoveryAccountDetails();
//
        if(recoveryAccountDetailsDTO!=null){
            recoveryAccountDetailsRepository.findById(userEntity.getId()).ifPresent(
                    recoveryAccountDetails ->{
                        dtoService.recoveryAccountDetails(recoveryAccountDetails,recoveryAccountDetailsDTO);
                        recoveryAccountDetailsRepository.save(recoveryAccountDetails);
                    }
            );
        }
        userEntityRepository.save(userEntity);
    }

    @Override
    public void updateEmail(String username, UsernameDetailDTO usernameDetailDTO) {
        UserEntity userEntity = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
        userEntity.setUsername(usernameDetailDTO.newUsername());
        userEntityRepository.save(userEntity);
    }

    @Override
    public void updatePassword(String username, PasswordDetailsDTO passwordDetails) {
        UserEntity userEntity = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
        String oldPassword = passwordDetails.oldPassword();

        if(!passwordEncoder.matches(oldPassword,userEntity.getPassword())){
            throw new InvalidUserDetailsException("The given password is not valid.");
        }
        userEntity.setPassword(passwordEncoder.encode(passwordDetails.newPassword()));

        userEntityRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void updateProfileImage(String username, MultipartFile profileImage) {
        if(!ALLOWED_FILE_EXTENSIONS.contains(profileImage.getContentType())){
            throw new ImageTypeNotValidException("Invalid image type: " + profileImage.getContentType());
        }
        UserEntity userEntity = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));

        Optional<ProfileAvatar> profileAvatarOptional = profileAvatarRepository.findById(userEntity.getId());

        profileAvatarOptional.ifPresentOrElse(avatar->{
                storageService.saveProfileAvatar(profileImage,avatar.getUrl());
        },()->{
            String profileUrl = utilService.generateUrlForAvatar(userEntity.getId(),profileImage.getOriginalFilename());
            ProfileAvatar profileAvatar = dtoService.getProfileAvatar(profileImage,profileUrl);
            profileAvatar.setUser(userEntity);
            profileAvatarRepository.save(profileAvatar);
            storageService.saveProfileAvatar(profileImage,profileUrl);
        });
    }

    @Override
    @Transactional
    public void deleteProfileAvatar(String username) {
        userEntityRepository.findByUsername(username).ifPresentOrElse(
                user -> {
                    Optional<ProfileAvatar> profileAvatarOptional = profileAvatarRepository.findById(user.getId());
                    profileAvatarOptional.ifPresentOrElse(
                            avatar -> {
                                storageService.deleteProfileAvatar(avatar.getUrl());
                                profileAvatarRepository.delete(avatar);
                            }, () -> {
                                throw new ProfileAvatarNotFoundException("Profile avatar not found for user: " + username);
                            }
                    );
                },
                () -> {
                    throw new UserNotFoundException("Invalid user data");
                }
        );
    }

    @Override
    public Boolean isValidUsername(String username) {
        if(!username.matches(USERNAME_PATTERN)){
            throw new InvalidUserDetailsException("The given username is not valid.");
        }
        return userEntityRepository.existsByUsername(username);
    }

}
