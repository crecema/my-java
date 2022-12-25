package com.crecema.my.java.algorithm.sort;

public class ShellSort implements Sort {
    @Override
    public void sort(int[] array) {
        int s = 1;
        while (s < array.length / 3) {
            s = s * 3 + 1;
        }
        for (; s >= 1; s /= 3) {
            for (int i = s; i < array.length; i++) {
                for (int j = i; j >= s; j -= s) {
                    if (array[j] < array[j - s]) {
                        change(array, j, j - s);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
