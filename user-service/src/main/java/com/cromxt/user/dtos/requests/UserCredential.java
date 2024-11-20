package com.cromxt.user.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record UserCredential(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
