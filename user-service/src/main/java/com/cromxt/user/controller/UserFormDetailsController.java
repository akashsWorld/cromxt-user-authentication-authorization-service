package com.cromxt.user.controller;


import com.cromxt.user.entity.CountryCode;
import com.cromxt.user.entity.Gender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/form/values")
public record UserFormDetailsController(
) {

    @GetMapping(value = "/countries")
    public Map<String,String> getCountries() {
        return Arrays.stream(CountryCode.values())
                .collect(Collectors.toMap(
                        country -> String.format("%s - %s", country.name(), country.name()),
                        CountryCode::name
                ));
    }
    @GetMapping(value = "/genders")
    public Map<String,String> getGenders() {
        return Arrays.stream(Gender.values())
                .collect(Collectors.toMap(
                        gender -> String.format("%s - %s", gender.name(), gender.getGender()),
                        Gender::name
                ));
    }
}
