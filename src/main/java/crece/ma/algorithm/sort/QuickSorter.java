package crece.ma.algorithm.sort;

public class QuickSorter<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(E[] array, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = partition(array, l, r);
        sort(array, l, m - 1);
        sort(array, m + 1, r);
    }

    private int partition(E[] array, int l, int r) {
        change(array, l , l + (r - l) / 2);
        int i = l;
        int j = r + 1;
        while (true) {
            while (array[++i].compareTo(array[l]) < 0) {
                if (i == r) {
                    break;
                }
            }
            while ((array[--j].compareTo(array[l]) > 0)) {
                if (j == l) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            change(array, i, j);
        }
        change(array, l, j); // 这一步很重要，必须要有
        return j;
    }

}
