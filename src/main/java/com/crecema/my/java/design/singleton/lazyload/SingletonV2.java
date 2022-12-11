package com.crecema.my.java.design.singleton.lazyload;

/**
 * 同步锁懒加载模式，线程安全
 */
public class SingletonV2 {

    private static SingletonV2 instance;

    private SingletonV2() {}

    // 相比V1，它是线程安全的，但是效率太低，99.99%不需要同步
    public static synchronized SingletonV2 getInstance() {
        if (instance == null) {
            instance = new SingletonV2();
        }
        return instance;
    }

}
