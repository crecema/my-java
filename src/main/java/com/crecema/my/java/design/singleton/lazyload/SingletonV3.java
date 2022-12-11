package com.crecema.my.java.design.singleton.lazyload;

/**
 * 双重同步锁懒加载模式，线程安全
 */
public class SingletonV3 {

    private static volatile SingletonV3 instance;

    private SingletonV3() {}

    // 相比V1，它是线程安全的，相比V2，他效率很高
    public static SingletonV3 getInstance() {
        if (instance == null) {
            synchronized (SingletonV3.class) {
                if (instance == null) {
                    instance = new SingletonV3();
                }
            }
        }
        return instance;
    }
}
