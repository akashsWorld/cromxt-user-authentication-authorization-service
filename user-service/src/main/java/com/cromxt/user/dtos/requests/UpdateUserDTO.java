package com.cromxt.user.dtos.requests;


public record UpdateUserDTO (
        String firstName,
        String lastName,
        RecoveryAccountDetailsDTO recoveryAccountDetails,
        String gender,
        String birthDate
){
}
