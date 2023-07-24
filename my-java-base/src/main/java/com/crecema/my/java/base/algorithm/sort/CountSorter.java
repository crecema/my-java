package com.crecema.my.java.base.algorithm.sort;

public class CountSorter implements Sorter {
    @Override
    public void sort(int[] array) {
        // 找出最小值和最大值
        int min = array[0];
        int max = array[0];
        for (int i : array) {
            if (i < min) min = i;
            if (i > max) max = i;
        }
        int length = max - min + 1;
        // 计数数组
        int[] countArray = new int[length];
        for (int i : array) {
            countArray[i - min]++;
        }
        int i = 0;
        for (int j = 0; j < length; j++) {
            while (countArray[j]-- > 0) {
                array[i++] = j + min;
            }
        }
    }
}
