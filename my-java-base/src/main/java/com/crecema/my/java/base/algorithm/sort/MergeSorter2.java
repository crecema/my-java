package com.crecema.my.java.base.algorithm.sort;

public class MergeSorter2 extends MergeSorter {
    @Override
    public void sort(int[] array) {
        temp = new int[array.length];
        int i = 1;
        while (i < array.length) {
            mergePass(array, i);
            i *= 2;
        }
        temp = null;
    }

    private void mergePass(int[] array, int gap) {
        int i = 0;
        while (i + 2 * gap - 1 < array.length) {
            merge(array, i, i + gap - 1, i + 2 * gap - 1);
            i += 2 * gap;
        }
        if (i + gap - 1 < array.length) {
            merge(array, i, i + gap - 1, array.length - 1);
        }
    }
}
