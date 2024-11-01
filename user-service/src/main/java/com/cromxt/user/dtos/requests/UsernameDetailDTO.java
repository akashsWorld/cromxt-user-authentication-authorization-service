package com.cromxt.user.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record UsernameDetailDTO(
        @NotBlank
        String newUsername
) {
}
