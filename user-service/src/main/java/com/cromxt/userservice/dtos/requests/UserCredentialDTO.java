package com.cromxt.userservice.dtos.requests;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record UserCredentialDTO(
        @NotBlank(message = "Username is required")
        @Length(min = 5,max = 20)
        String username,
        @NotBlank(message = "Password is required")
        @Length(min = 8,max = 16)
        String password
) {
}
