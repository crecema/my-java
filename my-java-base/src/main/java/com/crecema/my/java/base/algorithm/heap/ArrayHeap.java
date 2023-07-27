package com.crecema.my.java.base.algorithm.heap;

public class ArrayHeap implements Heap {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Type DEFAULT_TYPE = Type.MAX;

    private final Type type;
    private int capacity;
    private int size;

    private int[] data;

    public ArrayHeap() {
        this(DEFAULT_CAPACITY, DEFAULT_TYPE);
    }

    public ArrayHeap(int capacity) {
        this(capacity, DEFAULT_TYPE);
    }

    public ArrayHeap(Type type) {
        this(DEFAULT_CAPACITY, type);
    }

    public ArrayHeap(int capacity, Type type) {
        this.capacity = capacity;
        this.type = type;
        this.data = new int[capacity];
        this.size = 0;
    }

    @Override
    public void push(int value) {
        if (size + 1 > capacity) {
            reCapacity(capacity + 1);
        }
        data[size++] = value;
        swim(size - 1);
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        int value = data[0];
        swap(0, size - 1);
        data[--size] = 0;
        sink(0);
        if (size < capacity / 4) {
            reCapacity(capacity / 2);
        }
        return value;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        return data[0];
    }

    @Override
    public int size() {
        return size;
    }

    // 上浮 从下往上堆化
    private void swim(int i) {
        while (i > 0) {
            if (higher(data[i], data[root(i)])) {
                swap(i, root(i));
            }
            i = root(i);
        }
    }

    // 下沉 从上往下堆化
    private void sink(int i) {
        while (left(i) < size) {
            int j = left(i);
            if (right(i) < size && higher(data[right(i)], data[left(i)])) {
                j = right(i);
            }
            if (higher(data[j], data[i])) {
                swap(i, j);
            }
            i = j;
        }
    }

    private int root(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return i * 2 + 1;
    }

    private int right(int i) {
        return i * 2 + 2;
    }

    private boolean higher(int v1, int v2) {
        if (type == Type.MAX) {
            return v1 > v2;
        } else {
            return v1 < v2;
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void reCapacity(int minCapacity) {
        int newCapacity;
        if (minCapacity > capacity) {
            newCapacity = Math.max(minCapacity, capacity + (capacity >> 1));
        } else {
            newCapacity = Math.max(minCapacity, capacity >> 1);
        }
        int[] newData = new int[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
        capacity = newCapacity;
    }

}
