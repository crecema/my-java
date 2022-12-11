package com.crecema.my.java.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SorterTest {

    @Test
    public void testSelectSort() {
        runSort(new SelectSorter<>());
    }

    @Test
    public void testBubbleSort() {
        runSort(new BubbleSorter<>());
    }

    @Test
    public void testInsertSort() {
        runSort(new InsertSorter<>());
    }

    @Test
    public void testShellSort() {
        runSort(new ShellSorter<>());
    }

    @Test
    public void testMergeSort() {
        runSort(new MergeSorter<>());
    }

    @Test
    public void testQuickSort() {
        runSort(new QuickSorter<>());
    }

    @Test
    public void testCountSort() {
        Integer[] integers = genIntArray4Random(100);
        new CountSorter().sort(integers);
        assertTrue(checkArray(integers));
    }

    private Integer[] genIntArray4Random(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    private Integer[] genIntArray4Positive(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        for (int i = 0; i < size - 1; i += 10) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
        return array;
    }

    private Integer[] genIntArray4Reverse(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        for (int i = 0; i < size - 1; i += 10) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
        return array;
    }

    private Integer[] genIntArray4Repeat(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = size / 2;
        }
        for (int i = 0; i < size - 1; i += 10) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    private boolean checkArray(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void runSort(Sorter<Integer> sorter) {
        System.out.println(sorter.getClass().getSimpleName());

        Integer[] integers = genIntArray4Random(100000);
        LocalDateTime start = LocalDateTime.now();
        sorter.sort(integers);
        LocalDateTime end = LocalDateTime.now();
        assertTrue(checkArray(integers));
        System.out.println("Random: \t" + ((double) Duration.between(start, end).toMillis()) / 1000);

        integers = genIntArray4Positive(100000);
        start = LocalDateTime.now();
        sorter.sort(integers);
        end = LocalDateTime.now();
        assertTrue(checkArray(integers));
        System.out.println("Positive: \t" + ((double) Duration.between(start, end).toMillis()) / 1000);

        integers = genIntArray4Reverse(100000);
        start = LocalDateTime.now();
        sorter.sort(integers);
        end = LocalDateTime.now();
        assertTrue(checkArray(integers));
        System.out.println("Reverse: \t" + ((double) Duration.between(start, end).toMillis()) / 1000);

        integers = genIntArray4Repeat(100000);
        start = LocalDateTime.now();
        sorter.sort(integers);
        end = LocalDateTime.now();
        assertTrue(checkArray(integers));
        System.out.println("Repeat: \t" + ((double) Duration.between(start, end).toMillis()) / 1000);
    }

}