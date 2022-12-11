package com.crecema.my.java.design.singleton.lazyload;

/**
 * 内部类懒加载模式，线程安全
 * 创建一个多余的类，个人不建议这么干
 */
public class SingletonV4 {

    private SingletonV4() {}

    public static SingletonV4 getInstance() {
        return SingletonV4Holder.instance;
    }

    private static class SingletonV4Holder {
        private static final SingletonV4 instance = new SingletonV4();
    }

}
