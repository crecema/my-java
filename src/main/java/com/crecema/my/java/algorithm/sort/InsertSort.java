package com.crecema.my.java.algorithm.sort;

public class InsertSort implements Sort {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j] < array[j - 1]) {
                    change(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
