package crece.ma.algorithm.sort;

public class HeapSorter {

    interface MaxPQ<E extends Comparable<E>> {
        int size();
        default boolean isEmpty() {
            return size() == 0;
        }
        void add(E element);
        E getMax();
        E delMax();
    }

    static class ArrayMaxPQ<E extends Comparable<E>> implements MaxPQ<E> {

        public ArrayMaxPQ() {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public void add(E element) {

        }

        @Override
        public E getMax() {
            return null;
        }

        @Override
        public E delMax() {
            return null;
        }
    }
}
