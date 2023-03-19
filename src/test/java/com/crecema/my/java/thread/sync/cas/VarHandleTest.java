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
        System.out.println(signal.signal);
    }

}
