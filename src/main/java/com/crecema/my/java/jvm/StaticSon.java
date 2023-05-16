package com.crecema.my.java.jvm;

public class StaticSon extends StaticFather {

    private static String name = "son";

    static {
        System.out.println("Son static init block");
    }

    public static void main(String[] args) {
        System.out.println(name);
    }

}
