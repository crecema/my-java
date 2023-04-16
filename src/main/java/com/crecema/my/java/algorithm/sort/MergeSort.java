package com.crecema.my.java.algorithm.sort;

public class MergeSort implements Sort {

    private int[] temp;

    @Override
    public void sort(int[] array) {
        temp = new int[array.length];
        mergeSort(array, 0, array.length - 1);
        temp = null;
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);
        int l = left, r = mid + 1;
        for (int i = left; i <= right; i++) {
            if (l > mid) {
                array[i] = temp[r++];
            } else if (r > right) {
                array[i] = temp[l++];
            } else if (temp[l] <= temp[r]) {
                array[i] = temp[l++];
            } else {
                array[i] = temp[r++];
            }
        }
    }
}
