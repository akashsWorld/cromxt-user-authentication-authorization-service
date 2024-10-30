package com.cromxt.user.service;

import com.cromxt.user.dtos.requests.EmailDetailDTO;
import com.cromxt.user.dtos.requests.PasswordDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserDetailService {


    void saveUser(RegisterUserDTO registerUser);

    void updateUser(String email, UpdateUserDTO updateUserDTO);

    void updateEmail(String email, EmailDetailDTO newEmail);

    void updatePassword(String email, PasswordDetailsDTO passwordDetails);

    void updateProfileImage(String email, MultipartFile profileImage);

    void deleteProfileAvatar(String email);

    Boolean isEmailValid(String email);
}
