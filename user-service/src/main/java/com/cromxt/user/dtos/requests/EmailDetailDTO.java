package com.cromxt.user.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailDetailDTO(
        @Email
        @NotNull
        @NotBlank
        String newEmail
) {
}
