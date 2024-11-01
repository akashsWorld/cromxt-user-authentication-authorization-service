package com.cromxt.user.dtos.requests;

public record PasswordDetailsDTO(
        String oldPassword,
        String newPassword
) {
}
