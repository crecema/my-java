package crece.ma.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private int size = 0;

    private E[] elementData;

    public ArrayList() {
        elementData = E(EMPTY_ELEMENT_DATA);
    }

    public ArrayList(int capacity) {
        if (capacity == 0) {
            elementData = E(EMPTY_ELEMENT_DATA);
        } else if (capacity > 0) {
            elementData = E(new Object[capacity]);
        } else {
            throw new IllegalArgumentException("capacity must >= 0");
        }
    }

    public ArrayList(Collection<? extends E> elements) {
        Objects.requireNonNull(elements);
        E[] es = elements.toArray();
        if ((size = es.length) == 0) {
            elementData = E(EMPTY_ELEMENT_DATA);
        } else {
            elementData = es;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void addAll(Collection<? extends E> elements) {
        Objects.requireNonNull(elements);
        E[] es = elements.toArray();
        if (es.length == 0) {
            return;
        }
        int newSize = size + es.length;
        if (newSize > elementData.length) {
            grow(newSize);
        }
        System.arraycopy(es, 0, elementData, size, es.length);
        size = newSize;
    }

    @Override
    public void remove(E element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);
        }
    }

    @Override
    public void removeAll(Collection<? extends E> elements) {
        Objects.requireNonNull(elements);
        E[] es = elements.toArray();
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (!contains(es, elementData[i])) {
                elementData[index++] = elementData[i];
            }
        }
        for (int i = index; i < size; i++) {
            elementData[i] = null;
        }
        size = index;
    }

    private boolean contains(E[] elements, E element) {
        for (E e : elements) {
            if (e.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public boolean containsAll(Collection<? extends E> elements) {
        Objects.requireNonNull(elements);
        for (E element : elements) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
    }

    @Override
    public void add(int index, E element) {
        Objects.checkIndex(index, size + 1);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        elementData[size - 1] = null;
        size--;
        return oldElement;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldElement = elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Objects.checkFromToIndex(fromIndex, toIndex, size);
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public Iterator<E> iterator() {
        return this.new Itr();
    }

    @SuppressWarnings("unchecked")
    private E[] E(Object[] elementData) {
        return (E[]) elementData;
    }

    private void grow() {
        grow(size + 1);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity;
        if (oldCapacity == 0) {
            newCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
            elementData = E(new Object[newCapacity]);
        } else {
            newCapacity = Math.max(oldCapacity + oldCapacity >> 1, minCapacity);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private class Itr implements Iterator<E> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            Objects.checkIndex(cursor, size);
            return elementData[cursor++];
        }
    }

}
