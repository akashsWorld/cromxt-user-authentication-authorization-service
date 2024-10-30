package com.cromxt.user.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;


public record RecoveryAccountDetailsDTO(
        @Valid
        PhoneNumberDTO phoneNumber,
        @Email
        String recoveryEmail
) {
}
