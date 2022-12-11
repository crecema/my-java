package com.crecema.my.java.collection;

public interface Collection<E> extends Iterable<E> {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void add(E element);

    void addAll(Collection<? extends E> elements);

    void remove(E element);

    void removeAll(Collection<? extends E> elements);

    boolean contains(E element);

    boolean containsAll(Collection<? extends E> elements);

    E[] toArray();

    void clear();

}
