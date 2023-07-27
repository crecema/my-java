package com.crecema.my.java.base.algorithm.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapTests {

    @Test
    void testArrayMaxHeap() {
        Heap heap = new ArrayHeap(3);
        heap.push(3);
        heap.push(1);
        heap.push(2);
        heap.push(5);
        heap.push(4);
        heap.push(6);

        Assertions.assertEquals(6, heap.size());
        Assertions.assertEquals(6, heap.peek());
        Assertions.assertEquals(6, heap.pop());
        Assertions.assertEquals(5, heap.pop());
        Assertions.assertEquals(4, heap.size());
        heap.pop();
        heap.pop();
        heap.pop();
        Assertions.assertEquals(1, heap.size());
        heap.pop();
        Assertions.assertTrue(heap.isEmpty());
    }

}
