package crece.ma.algorithm.sort;

public class InsertSorter <E extends Comparable<E>> implements Sorter<E> {

    @Override
    public void sort(E[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    change(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

}
