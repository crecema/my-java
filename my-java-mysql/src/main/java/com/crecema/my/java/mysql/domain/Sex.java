package com.crecema.my.java.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Sex {

    FEMALE(0, "FEMALE"),
    MALE(1, "MALE");

    private final int code;
    private final String desc;

    public static Sex valueOf(int code) {
        return Arrays.stream(Sex.values())
                .filter(sex -> sex.code == code)
                .findFirst()
                .orElse(null);
    }

}