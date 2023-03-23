package com.crecema.my.java.thread.sync.cas;

import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VarHandleTest {

    static class Signal {
        boolean signal = false;
    }

    @Test
    public void testCAS() throws NoSuchFieldException, IllegalAccessException {
        Signal signal = new Signal();
        VarHandle varHandle = MethodHandles.lookup().findVarHandle(Signal.class, "signal", boolean.class);
        varHandle.compareAndSet(signal, false, true);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            signal.signal = false;
        }).start();

        while (!varHandle.compareAndSet(signal, false, true)) {}
        System.out.println("抢到锁啦，自旋结束");
    }

}
