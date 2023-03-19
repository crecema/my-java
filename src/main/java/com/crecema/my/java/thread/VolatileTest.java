package com.crecema.my.java.thread;

class MyData {
    int sign = 0;
}

public class VolatileTest {

    public static void main(String[] args) {

        MyData data = new MyData();

        new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            data.sign = 1;
        }).start();

        int b = 0;

        // 如果sign没有被Volatile修饰，这个循环会一直持续下去
        while (data.sign == 0) {}
        System.out.println(b);
    }
}
