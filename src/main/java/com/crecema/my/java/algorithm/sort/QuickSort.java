package com.crecema.my.java.algorithm.sort;

public class QuickSort implements Sort {
    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * 部分排序
     */
    private void sort(int[] array, int l, int r) {
        if (r <= l) {
            return;
        }
        int p = partition(array, l, r);
        sort(array, l, p - 1);
        sort(array, p + 1, r);
    }

    /**
     * 将一个无序段分成两个相邻有序段
     */
    private int partition(int[] array, int l, int r) {
        change(array, l , l + (r - l) / 2);
        int i = l;
        int j = r + 1;
        while (true) {
            while (array[++i] < array[l]) {
                if (i == r) {
                    break;
                }
            }
            while ((array[--j] > array[l])) {
                if (j == l) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            change(array, i, j);
        }
        change(array, l, j);
        return j;
    }
}
