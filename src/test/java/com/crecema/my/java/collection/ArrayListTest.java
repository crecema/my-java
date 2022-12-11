package com.crecema.my.java.collection;

import org.junit.jupiter.api.Test;

public class ArrayListTest {

    @Test
    public void testArrayList() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>(0);
        List<Integer> list3 = new ArrayList<>(5);
        for (int i = 0 ; i < 5; i++) list3.add(i);
        List<Integer> list4 = new ArrayList<>(list3);
        System.out.println(list4);
    }

    @Test
    public void testRemoveAll() {
        List<Integer> srcList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            srcList.add(i);
        }
        System.out.println(srcList);
        List<Integer> removedList = new ArrayList<>();
        for (int i = 0; i < 10; i+=2) {
            removedList.add(i);
        }
        System.out.println(removedList);
        srcList.removeAll(removedList);
        System.out.println(srcList);
    }

}
