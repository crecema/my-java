package com.crecema.my.java.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("all")
public class NullPointerExceptionTest {

    @Test
    public void test() {
        Assertions.assertThrows(NullPointerException.class, () ->  {
            Integer a = 1, b = null;
            Integer c = add(a, b);
        });
    }

    private int add(int a, int b) {
        return a + b;
    }

}
