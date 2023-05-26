package com.crecema.my.java.base.concurrent.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;

public class ThreadLocalTest {

    @Test
    public void testThreadLocal() throws InterruptedException {

        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal1.set("a");
            threadLocal2.set("aa");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Assertions.assertEquals("a", threadLocal1.get());
            Assertions.assertEquals("aa", threadLocal2.get());
        }).start();

        new Thread(() -> {
            threadLocal1.set("b");
            threadLocal2.set("bb");
            Assertions.assertEquals("b", threadLocal1.get());
            Assertions.assertEquals("bb", threadLocal2.get());
        }).start();

        Thread.sleep(1000);
        threadLocal1.remove();
        threadLocal2.remove();
    }

    @Test
    public void testWeakReference() {
        WeakReference<String> stringReference = new WeakReference<>("test_string");
        System.out.println(stringReference.get());
        // 某些情况下，stringReference 可能引用不到 "test_string"，此时get()方法返回null
    }

    private static class Entry extends WeakReference<String> {

        public Entry(String referent) {
            super(referent);
        }
    }

}
