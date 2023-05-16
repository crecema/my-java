package com.crecema.my.java.jvm;

public class StaticFather {

    private static String name = "father";

    static {
        System.out.println("Father static init block");
    }

}
