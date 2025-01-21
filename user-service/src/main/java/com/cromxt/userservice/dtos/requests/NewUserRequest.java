package com.cromxt.userservice.dtos.requests;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewUserRequest(
        @NotBlank
        @Length(min = 5,max = 20)
        String username,
        @NotBlank
        @Length(min = 8,max = 16)
        String password,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 3,max = 20)
        String firstName,
        @NotBlank
        @Length(min = 3,max = 20)
        String lastName,
        @NotBlank
        @Length(min = 3,max = 6)
        String gender,
        @NotBlank
        Date dateOfBirth,
        MultipartFile avatar
) {
}
