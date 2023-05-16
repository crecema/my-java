package com.crecema.my.java.design.creational.singleton.preload;

/**
 * 简单的预加载模式，线程安全
 */
public class SingletonV2 {

    private static final SingletonV2 instance;

    static {
        instance = new SingletonV2();
        // 相比与V1，这里可以多做一些init逻辑
    }

    private SingletonV2() {}

    public static SingletonV2 getInstance() {
        return instance;
    }

}
