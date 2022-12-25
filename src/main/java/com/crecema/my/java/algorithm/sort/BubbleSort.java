package com.crecema.my.java.algorithm.sort;

public class BubbleSort implements Sort {
    @Override
    public void sort(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    change(array, j, j + 1);
                }
            }
        }
    }
}
