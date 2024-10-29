package com.cromxt.user.service;

import com.cromxt.user.dtos.UserDetailDTO;

public interface UserEntityService {


    void saveUser(UserDetailDTO registerUser);

    void updateUser(UserDetailDTO userDetail);
}
