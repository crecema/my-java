package com.crecema.my.java.base.algorithm.sort;

public class ShellSorter implements Sorter {
    @Override
    public void sort(int[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                int temp = array[i];
                int j = i;
                while (j >= h && array[j - h] > temp) {
                    array[j] = array[j - h];
                    j = j - h;
                }
                array[j] = temp;
            }
            h = h / 3;
        }
    }
}
