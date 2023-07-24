package com.crecema.my.java.base.algorithm.sort;

public interface Sorter {

    /**
     * 将给定整型数组从小到大排序
     * 原地排序
     */
    void sort(int[] array);

    /**
     * 交换数组中的两个元素
     */
    default void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
