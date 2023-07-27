package com.crecema.my.java.base.algorithm.sort;

import com.crecema.my.java.base.algorithm.heap.ArrayHeap;
import com.crecema.my.java.base.algorithm.heap.Heap;

public class HeapSorter implements Sorter {
    @Override
    public void sort(int[] array) {
        Heap heap = new ArrayHeap(array.length, Heap.Type.MIN);
        for (int j : array) {
            heap.push(j);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }
    }
}
