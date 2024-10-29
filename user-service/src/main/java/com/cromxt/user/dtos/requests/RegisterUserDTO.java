package com.cromxt.user.dtos.requests;

import com.cromxt.user.validation.GenderValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public record RegisterUserDTO(
        @Email
        @NotBlank
        @NotNull
        String email,
        @Length(min = 8, max = 16)
        String password,
        @NotNull
        @NotBlank
        String firstName,
        @NotNull
        @NotBlank
        String lastName,
        @GenderValidation
        String gender,
        String birthDate,
        RecoveryAccountDetailsDTO recoveryAccountDetailsDTO
) {
}
