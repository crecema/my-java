package com.crecema.my.java.algorithm.sort;

public interface Sort {

    void sort(int[] array);

    default void change(int[] array, int l, int r) {
        int tmp = array[l];
        array[l] = array[r];
        array[r] = tmp;
    }

}
