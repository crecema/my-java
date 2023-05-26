package com.crecema.my.java.base.concurrent.impl;

import com.crecema.my.java.base.concurrent.Counter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

public class MyMutexLockTest {

    @Test
    public void testLock() throws InterruptedException {
        Counter counter = new Counter();
        Lock lock = new MyMutexLock();
        Thread a = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                counter.increase(1);
                lock.unlock();
            }
        });
        Thread b = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                counter.reduce(1);
                lock.unlock();
            }
        });
        Thread c = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                counter.increase(1);
                lock.unlock();
            }
        });
        Thread d = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                counter.reduce(1);
                lock.unlock();
            }
        });
        a.start(); b.start(); c.start(); d.start();
        a.join(); b.join(); c.join(); d.join();
        Assertions.assertEquals(0, counter.getCount());
    }

}
