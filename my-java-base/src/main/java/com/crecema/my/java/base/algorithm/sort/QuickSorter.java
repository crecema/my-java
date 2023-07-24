package com.crecema.my.java.base.algorithm.sort;

public class QuickSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    // 递归方法, 将给定区间[left, right]的数组进行排序
    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(array, left, right);
        quickSort(array, left, index - 1);
        quickSort(array, index, right);
    }

    // 分区方法, 将给定区间[left, right]的数组分区, 并返回分区点的下标
    private int partition(int[] array, int left, int right) {
        int pivot = array[(left + right) / 2];
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
}
