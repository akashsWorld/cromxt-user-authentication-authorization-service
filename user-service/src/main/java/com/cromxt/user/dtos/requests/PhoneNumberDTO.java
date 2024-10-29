package com.cromxt.user.dtos.requests;

import com.cromxt.user.entity.CountryCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PhoneNumberDTO(
        @NotBlank
        @NotBlank
        CountryCode countryCode,
        @NotNull
        @NotBlank
        String phoneNumber
) {
}
