package com.crecema.my.java.collection;

public interface List<E> extends Collection<E> {

    void add(int index, E element);

    E remove(int index);

    E get(int index);

    E set(int index, E element);

    int indexOf(E element);

    int lastIndexOf(E element);

    List<E> subList(int fromIndex, int toIndex);

}
