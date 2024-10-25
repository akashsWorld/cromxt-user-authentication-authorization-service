package com.cromxt.user.entity;


import lombok.Getter;

public enum CountryCode {
    INDIA("+91"),
    AMERICA("+1");
    @Getter
    private final String code;

    private CountryCode(String code) {
        this.code = code;
    }

}
