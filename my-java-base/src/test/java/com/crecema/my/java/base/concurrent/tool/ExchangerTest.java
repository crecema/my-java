package com.crecema.my.java.base.concurrent.tool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

public class ExchangerTest {

    @Test
    public void test() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                String name = "Thread 1";
                System.out.println(Thread.currentThread().getName() + ": now my name is " + name);
                name = exchanger.exchange(name);
                System.out.println(Thread.currentThread().getName() + ": now my name is " + name);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                String name = "Thread 2";
                System.out.println(Thread.currentThread().getName() + ": now my name is " + name);
                name = exchanger.exchange(name);
                System.out.println(Thread.currentThread().getName() + ": now my name is " + name);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        countDownLatch.await();
    }

}
