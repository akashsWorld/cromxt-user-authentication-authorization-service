package com.cromxt.user.dtos.requests;

import com.cromxt.user.validation.GenderValidation;

public record UpdateUserDTO (
        String firstName,
        String lastName,
        RecoveryAccountDetailsDTO recoveryAccountDetailsDTO,
        @GenderValidation
        String gender,
        String birthDate
){
}
