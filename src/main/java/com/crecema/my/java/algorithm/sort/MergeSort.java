package com.crecema.my.java.algorithm.sort;

public class MergeSort implements Sort {

    private int[] tmp;

    @Override
    public void sort(int[] array) {
        tmp = new int[array.length];
        sort(array, 0, array.length - 1);
    }

    /**
     * 部分排序
     */
    private void sort(int[] array, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2;
        sort(array, l, m);
        sort(array, m + 1, r);
        merge(array, l, m, r);
    }

    /**
     * 将数组中两个相邻段有序段合并为一个有序段
     */
    private void merge(int[] array, int l, int m, int r) {
        System.arraycopy(array, l, tmp, l, r + 1 - l);
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i > m) {
                array[k] = tmp[j++];
            } else if (j > r) {
                array[k] = tmp[i++];
            } else {
                array[k] = array[i] < array[j]
                        ? array[i++]
                        : array[j++];
            }
        }
    }
}
