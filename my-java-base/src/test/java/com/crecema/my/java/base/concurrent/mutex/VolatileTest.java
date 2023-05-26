package com.crecema.my.java.base.concurrent.mutex;

import org.junit.jupiter.api.Test;

public class VolatileTest {

    static class Signal {
        volatile boolean signal = false;
    }

    @Test
    public void testVolatile() {
        Signal signal = new Signal();
        Thread a = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            signal.signal = true;
        });
        a.start();
        while (!signal.signal) {
            // 如果signal不是volatile，这里会一直循环
            // 这里条件比较苛刻，有执行语句可能不会触发，do-while也不行
        }
        System.out.println("信号来了");
    }

}
