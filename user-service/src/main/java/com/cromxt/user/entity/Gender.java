package com.cromxt.user.entity;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("MALE"),FEMALE("FEMALE");
    private final String gender;


    private Gender(String gender) {
        this.gender = gender;
    }
}
