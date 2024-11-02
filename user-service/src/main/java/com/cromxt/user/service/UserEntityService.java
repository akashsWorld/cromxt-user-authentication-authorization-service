package com.cromxt.user.service;

import com.cromxt.user.dtos.requests.*;
import org.springframework.web.multipart.MultipartFile;

public interface UserEntityService {


    void saveUser(RegisterUserDTO registerUser);

    void updateUser(String username, UpdateUserDTO updateUserDTO);

    void updateEmail(String username, UsernameDetailDTO newEmail);

    void updatePassword(String username, PasswordDetailsDTO passwordDetails);

    void updateProfileImage(String username, MultipartFile profileImage);

    void deleteProfileAvatar(String username);

    Boolean isValidUsername(String username);
}
