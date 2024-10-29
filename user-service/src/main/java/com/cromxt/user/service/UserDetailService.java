package com.cromxt.user.service;

import com.cromxt.user.dtos.requests.EmailDetailDTO;
import com.cromxt.user.dtos.requests.PasswordDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.dtos.requests.UpdateUserDTO;

public interface UserDetailService {


    void saveUser(RegisterUserDTO registerUser);

    void updateUser(String email, UpdateUserDTO updateUserDTO);

    void updateEmail(String email, EmailDetailDTO newEmail);

    void updatePassword(String email, PasswordDetailsDTO passwordDetails);
}
