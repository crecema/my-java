package com.crecema.my.java.concurrent.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyShareLock implements Lock {

    private int number;

    public MyShareLock(int number) {
        this.number = number;
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int number) {
            setState(number);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            while (true) {
                int state = getState();
                if (compareAndSetState(state, state + 1)) {
                    return state + 1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return compareAndSetState(getState(), getState() + 1);
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }

        protected ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    private Sync sync = new Sync(number);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return sync.releaseShared(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
