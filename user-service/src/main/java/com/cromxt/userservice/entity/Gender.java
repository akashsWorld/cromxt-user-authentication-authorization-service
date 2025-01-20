package com.cromxt.userservice.entity;

import lombok.Getter;


@Getter
public enum Gender {
    
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Others");

    private String name;
    private Gender(String value){
        this.name=value;
    }

    
}
