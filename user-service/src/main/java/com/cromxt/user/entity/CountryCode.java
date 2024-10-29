package com.cromxt.user.entity;


import lombok.Getter;

@Getter
public enum CountryCode {
    INDIA("+91"),
    AMERICA("+1");
    private final String code;

    private CountryCode(String code) {
        this.code = code;
    }

}
