package com.crecema.my.java.base.jvm;

public class StaticFather {

    private static String name = "father";

    static {
        System.out.println("Father static init block");
    }

}
