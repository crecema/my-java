package com.crecema.my.java.thread.sync.cas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {

    static class CasLock {

        private int lockFlag = 0;

        VarHandle lockFlagHandle;
        {
            try {
                lockFlagHandle = MethodHandles.lookup().findVarHandle(CasLock.class, "lockFlag", int.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void lock() {
            while (!lockFlagHandle.compareAndSet(this, 0, 1)) {
                System.out.println("try lock ......");
            }
        }
        public void unlock() {
            lockFlagHandle.compareAndSet(this, 1, 0);
        }
    }

    static class CasLockV2 {

        private final AtomicInteger lockFlag = new AtomicInteger(0);

        public void lock() {
            while (!lockFlag.compareAndSet(0, 1)) {
                System.out.println("try lock ......");
            }
        }

        public void unlock() {
            lockFlag.compareAndSet(1, 0);
        }

    }

    static class Counter {
        private int count = 0;
        public int getCount() {
            return this.count;
        }
        public void increase(int n) {
            this.count += n;
        }
        public void reduce(int n) {
            this.count -= n;
        }
    }

    @Test
    public void testLock() throws InterruptedException {
        Counter counter = new Counter();
        CasLock lock = new CasLock();
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
                counter.increase(1);
                lock.unlock();
            }
        });
        Thread c = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                counter.reduce(1);
                lock.unlock();
            }
        });
        a.start(); b.start(); c.start();
        a.join(); b.join(); c.join();
        Assertions.assertEquals(10000, counter.getCount());
    }
}
