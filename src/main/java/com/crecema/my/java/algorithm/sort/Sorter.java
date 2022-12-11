package com.crecema.my.java.algorithm.sort;

public interface Sorter<E extends Comparable<E>> {

    void sort(E[] array);

    default void change(E[] array, int l, int r) {
        E temp = array[l];
        array[l] = array[r];
        array[r] = temp;
    }

}
