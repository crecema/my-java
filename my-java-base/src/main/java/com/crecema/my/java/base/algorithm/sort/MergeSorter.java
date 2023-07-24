package com.crecema.my.java.base.algorithm.sort;

public class MergeSorter implements Sorter {

    private int[] temp;

    @Override
    public void sort(int[] array) {
        temp = new int[array.length];
        mergeSort(array, 0, array.length - 1);
        temp = null;
    }

    // 递归方法, 将给定区间[left, right]的数组进行排序
    private void mergeSort(int[] array, int left, int right) {
        // 递归出口
        if (left >= right) {
            return;
        }
        // 分治
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        // 合并
        merge(array, left, mid, right);
    }

    // 合并方法, 将给定区间[left, mid]和[mid + 1, right]的数组合并
    private void merge(int[] array, int left, int min, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);
        int l = left, r = min + 1;
        for (int i = left; i <= right; i++) {
            if (l > min) {
                array[i] = temp[r++];
            } else if (r > right) {
                array[i] = temp[l++];
            } else if (temp[l] < temp[r]) {
                array[i] = temp[l++];
            } else {
                array[i] = temp[r++];
            }
        }
    }
}
