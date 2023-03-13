package com.crecema.my.java;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {
        List<Integer> integerList = List.of();
        System.out.println(integerList.stream().findFirst().orElse(null));
    }

}
