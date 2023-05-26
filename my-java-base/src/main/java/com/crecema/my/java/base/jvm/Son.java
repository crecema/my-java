package com.crecema.my.java.base.jvm;

public class Son extends Father {

    private String name = "son";

    {
        System.out.println(name);
        System.out.println("Son init block");
    }

    public Son() {
        name = "son";
        System.out.println("Son constructor");
    }

    private int age = 50;

    public static void main(String[] args) {
        new Son();
    }

}
