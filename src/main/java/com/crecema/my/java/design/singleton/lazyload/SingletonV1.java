package com.crecema.my.java.design.singleton.lazyload;

/**
 * 简单懒加载模式，线程不安全
 */
public class SingletonV1 {

    private static SingletonV1 instance;

    private SingletonV1() {}

    public static SingletonV1 getInstance() {
        if (instance == null) {
            instance = new SingletonV1();
        }
        return instance;
    }

}
