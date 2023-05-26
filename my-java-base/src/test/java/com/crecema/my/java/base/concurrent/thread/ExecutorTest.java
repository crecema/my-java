package com.crecema.my.java.base.concurrent.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorTest {

    @Test
    public void testExecutors() {
        // ThreadPoolExecutor -> ExecutorService -> Executor
        ExecutorService executorService;

        // FixedThreadPool 使用固定线程数 + 无界阻塞队列
        executorService = Executors.newFixedThreadPool(5);

        // SingleThreadExecutor 使用单一线程数 + 无界阻塞队列
        executorService = Executors.newSingleThreadExecutor();

        // CachedThreadPool 使用无数线程数 + 不存储元素的阻塞队列
        executorService = Executors.newCachedThreadPool();

        // ScheduledThreadPoolExecutor -> ScheduledExecutorService -> ExecutorService -> Executor
        ScheduledExecutorService scheduledExecutorService;

        // ScheduledThreadPool
        scheduledExecutorService = Executors.newScheduledThreadPool(5);

        // SingleThreadScheduledExecutor
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    }

    @Test
    public void testRunnable() throws InterruptedException {
        // 使用Executors创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable sleepTask = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": I will sleep 1s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // 向线程池提交任务
        for (int i = 0 ; i < 7; i++) {
            executorService.execute(sleepTask);
        }

        Thread.sleep(11000);
    }

}
