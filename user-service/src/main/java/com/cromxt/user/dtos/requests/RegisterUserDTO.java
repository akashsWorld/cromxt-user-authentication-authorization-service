package com.cromxt.user.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;


public record RegisterUserDTO(
        @NotBlank
        String username,
        @Length(min = 8, max = 16)
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String gender,
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
        String birthDate,
        @Valid
        RecoveryAccountDetailsDTO recoveryAccountDetails

) {
}
