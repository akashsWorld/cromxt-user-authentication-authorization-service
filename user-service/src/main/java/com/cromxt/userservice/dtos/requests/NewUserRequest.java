package com.cromxt.userservice.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

public record NewUserRequest(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String gender,
        String dateOfBirth,
        MultipartFile avatar
) {
}
