package com.crecema.my.java.base.algorithm.sort;

import java.util.PriorityQueue;

public class HeapSorter2 implements Sorter {
    @Override
    public void sort(int[] array) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(array.length);
        for (int i : array) {
            queue.add(i);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = queue.remove();
        }
    }
}
