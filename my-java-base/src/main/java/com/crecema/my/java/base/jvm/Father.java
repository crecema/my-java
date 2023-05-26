package com.crecema.my.java.base.jvm;

public class Father {

    private String name = "father";

    {
        System.out.println("Father init block");
    }

    private int age = 50;

    public Father() {
        name = "father";
        System.out.println("Father constructor");
    }

}
