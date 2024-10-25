package com.cromxt.user.dtos;

import com.cromxt.user.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RegisterUser(
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

        String phoneNumber,
        String domain,
        Gender gender,
        LocalDate birthday
) {
}
