package crece.ma.algorithm.sort;

public class SelectSorter<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public void sort(E[] array) {
        for (int i = 0; i < array.length; i++) {
            int m = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[m]) < 0) {
                    m = j;
                }
            }
            change(array, m, i);
        }
    }

}
