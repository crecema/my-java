package com.crecema.my.java.algorithm.sort;

public class SelectSort implements Sort {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int m = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[m]) {
                    m = j;
                }
            }
            change(array, m, i);
        }
    }
}
