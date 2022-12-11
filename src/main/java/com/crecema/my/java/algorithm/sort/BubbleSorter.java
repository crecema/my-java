package com.crecema.my.java.algorithm.sort;

public class BubbleSorter<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public void sort(E[] array) {
        for (int i = array.length - 1; i >= 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    change(array, j, j+1);
                }
            }
        }
    }

}
