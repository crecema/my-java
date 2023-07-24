package com.crecema.my.java.base.algorithm.sort;

public class SelectSorter implements Sorter {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int m = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[m]) {
                    m = j;
                }
            }
            swap(array, i, m);
        }
    }
}
