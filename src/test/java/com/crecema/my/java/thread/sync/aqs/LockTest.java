package com.crecema.my.java.thread.sync.aqs;

import com.crecema.my.java.thread.sync.Counter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    @Test
    public void testLock() throws InterruptedException {
        Counter counter = new Counter();
        Lock lock = new ReentrantLock();
        Thread a = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                counter.increase(1);
                lock.unlock();
            }
        });
        Thread b = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.reduce(1);
            }
        });
        Thread c = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increase(1);
            }
        });
        Thread d = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.reduce(1);
            }
        });
        a.start(); b.start(); c.start(); d.start();
        a.join(); b.join(); c.join(); d.join();
        Assertions.assertEquals(0, counter.getCount());
    }

}
