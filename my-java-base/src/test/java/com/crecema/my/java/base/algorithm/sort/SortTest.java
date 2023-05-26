package com.crecema.my.java.base.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortTest {

    @Test
    public void testSelectSort() {
        runSort(new SelectSort());
    }

    @Test
    public void testBubbleSort() {
        runSort(new BubbleSort());
    }

    @Test
    public void testInsertSort() {
        runSort(new InsertSort());
    }

    @Test
    public void testShellSort() {
        runSort(new ShellSort());
    }

    @Test
    public void testMergeSort() {
        runSort(new MergeSort());
    }

    @Test
    public void testQuickSort() {
        runSort(new QuickSort());
    }

    private void runSort(Sort sorter) {
        System.out.println(sorter.getClass().getSimpleName());

        int[] integers = genIntArray4Random(100000);
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
        System.out.println();
    }

    private int[] genIntArray4Random(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    private int[] genIntArray4Positive(int size) {
        int[] array = new int[size];
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

    private int[] genIntArray4Reverse(int size) {
        int[] array = new int[size];
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

    private int[] genIntArray4Repeat(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size / 2;
        }
        for (int i = 0; i < size - 1; i += 10) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    private boolean checkArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
