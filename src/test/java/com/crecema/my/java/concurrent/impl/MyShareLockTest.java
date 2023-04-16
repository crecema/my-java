package com.crecema.my.java.concurrent.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

public class MyShareLockTest {

    @Test
    public void testLock() throws InterruptedException {
        Lock lock = new MyShareLock(2);
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                System.out.println("hello");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        };
        Thread a = new Thread(task);
        Thread b = new Thread(task);
        Thread c = new Thread(task);
        Thread d = new Thread(task);
        a.start(); b.start(); c.start(); d.start();
        a.join(); b.join(); c.join(); d.join();
    }

}
