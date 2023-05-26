package com.crecema.my.java.base.algorithm.sort;

public class ShellSort implements Sort {
    @Override
    public void sort(int[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && array[j] < array[j - h]; j -= h) {
                    swap(array, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
