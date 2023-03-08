package com.crecema.my.java.algorithm.offer;

import java.util.LinkedList;

public class Offer09 {

    static class CQueue {

        LinkedList<Integer> stackA, stackB;

        public CQueue() {
            stackA = new LinkedList<>();
            stackB = new LinkedList<>();
        }

        public void appendTail(int value) {
            stackA.push(value);
        }

        public int deleteHead() {
            if (stackB.isEmpty()) {
                while (!stackA.isEmpty()) {
                    stackB.push(stackA.pop());
                }
            }
            if (stackB.isEmpty()) {
                return -1;
            } else {
                return stackB.pop();
            }
        }
    }
}
