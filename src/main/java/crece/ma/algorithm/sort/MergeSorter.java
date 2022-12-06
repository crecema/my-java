package crece.ma.algorithm.sort;

import java.util.Arrays;

public class MergeSorter<E extends Comparable<E>> implements Sorter<E> {

    private E[] tmpe;

    @Override
    public void sort(E[] array) {
        tmpe = Arrays.copyOf(array, array.length);
        sort(array, 0, array.length - 1);
    }

    private void sort(E[] array, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2;
        sort(array, l, m);
        sort(array, m + 1, r);
        merge(array, l, m, r);
    }

    private void merge(E[] array, int l, int m, int r) {
        System.arraycopy(array, l, tmpe, l, r + 1 - l);
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i > m) {
                array[k] = tmpe[j++];
            } else if (j > r) {
                array[k] = tmpe[i++];
            } else {
                array[k] = array[i].compareTo(array[j]) < 0
                        ? array[i++]
                        : array[j++];
            }
        }
    }

    public void sortV2(E[] array) {
        tmpe = Arrays.copyOf(array, array.length);
        for (int i = 1; i < array.length; i *= 2) {
            for (int j = 0; j < array.length - i; j += 2 * i) {
                merge(array, j, j + i - 1, Math.min(array.length - 1, j + 2 * i - 1));
            }
        }
    }

}
