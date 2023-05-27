package com.crecema.my.java.base.algorithm.binary;

public class BinarySearch {

    public Integer search(int[] array, int target) {
        int left = 0, right = array.length - 1;
        int middle;

        while (left <= right) {
            middle = left + (right - left) / 2;
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] > target) {
                right = middle - 1;
            } else if (array[middle] < target) {
                left = middle + 1;
            }
        }

        return -1;
    }

}
