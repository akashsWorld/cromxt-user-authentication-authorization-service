package com.cromxt.user.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Valid
public record RecoveryAccountDetailsDTO(
        PhoneNumberDTO phoneNumberDTO,
        @NotBlank
        @Email
        String recoveryEmail
) {
}
