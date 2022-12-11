package com.crecema.my.java.algorithm.sort;

public class ShellSorter <E extends Comparable<E>> implements Sorter<E> {

    private int rank = 3;

    public ShellSorter() {}

    public ShellSorter(int rank) {
        this.rank = rank;
    }

    @Override
    public void sort(E[] array) {
        int s = 1;
        while (s < array.length / rank) {
            s = s * rank + 1; // 1 4 13 ...
        }
        for (; s >= 1; s /= rank) {
            for (int i = s; i < array.length; i++) {
                for (int j = i; j >= s; j -= s) {
                    if (array[j].compareTo(array[j - s]) < 0) {
                        change(array, j, j - s);
                    } else {
                        break;
                    }
                }
            }
        }
    }

}
