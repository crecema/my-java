package com.crecema.my.java.thread.sync;

public class Counter {

    private int count = 0;

    public void increase(int n) {
        count += n;
    }

    public void reduce(int n) {
        count -= n;
    }

    public int getCount() {
        return count;
    }

    public synchronized void syncIncrease(int n) {
        count += n;
    }

    public synchronized void syncReduce(int n) {
        count -= n;
    }

    public synchronized int syncGetCount() {
        return count;
    }

}
