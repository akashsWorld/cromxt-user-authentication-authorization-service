package com.cromxt.user.controller;


import com.cromxt.user.entity.CountryCode;
import com.cromxt.user.entity.Gender;
import com.cromxt.user.service.UserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/form/values")
public record UserFormDetailsController(
        UserDetailService userDetailService
) {

    @GetMapping(value = "/countries")
    public Map<String,String> getCountries() {
        return Arrays.stream(CountryCode.values())
                .collect(Collectors.toMap(
                        country -> String.format("%s - %s", country.name(), country.getCode()),
                        CountryCode::getCode
                ));
    }
    @GetMapping(value = "/genders")
    public Map<String,String> getGenders() {
        return Arrays.stream(Gender.values())
                .collect(Collectors.toMap(
                        gender -> String.format("%s - %s", gender.name(), gender.getGender()),
                        Gender::getGender
                ));
    }

    @GetMapping(value = "/validate/{email}")
    public ResponseEntity<Boolean> isUsernameValid(@PathVariable String email) {
        return ResponseEntity.ok(userDetailService.isValidUsername(email));
    }
}
