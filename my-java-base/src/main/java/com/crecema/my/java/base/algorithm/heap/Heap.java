package com.crecema.my.java.base.algorithm.heap;

public interface Heap {

    void push(int value);

    int pop();

    int peek();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    enum Type {
        MIN, MAX
    }

}
