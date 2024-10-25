package com.cromxt.user.entity;

import lombok.Getter;

public enum Gender {
    MALE("Male"),FEMALE("Female");
    @Getter
    private final String gender;

    private Gender(String gender) {
        this.gender = gender;
    }
}
