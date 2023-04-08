package com.crecema.my.java.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadTest {

    @Test
    public void testRunnable() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("I will sleep 1s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread newThread = new Thread(task);
        newThread.start();

        newThread.join();
    }

    @Test
    public void testCallable() throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("I will sleep 1s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return 0;
        };

        // Callable不能直接传给Thread，需要使用FutureTask包一层
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        Thread newThread = new Thread(futureTask);
        newThread.start();

        // newThread.join();
        System.out.println(futureTask.get());
    }

    @Test
    public void testCallableCancel() throws Exception {
        Callable<Integer> task = () -> {
            for (int i = 0; i < 10; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Oh, I am be interrupted!!!!");
                    return 1;
                }
                System.out.println("I will sleep 1s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return 0;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread newThread = new Thread(futureTask);
        newThread.start();

        // 先等它执行5秒
        newThread.join(5000);

        if (futureTask.isDone()) {
            System.out.println("it's end, result=" + futureTask.get());
        } else {
            futureTask.cancel(true);
            newThread.join();
            System.out.println("not waiting，cancel it");
        }

    }

}
