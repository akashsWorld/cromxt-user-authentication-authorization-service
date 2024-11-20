package com.cromxt.user.controller;


import com.cromxt.user.entity.CountryCode;
import com.cromxt.user.entity.Gender;
import com.cromxt.user.service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/form")
public record UserFormDetailsController(
        UserEntityService userEntityService
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

    @GetMapping(value = "/validate/{username}")
    public ResponseEntity<Boolean> isUsernameValid(@PathVariable String username) {
        return ResponseEntity.ok(userEntityService.isValidUsername(username));
    }
}
