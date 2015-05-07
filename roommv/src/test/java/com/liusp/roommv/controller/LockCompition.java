package com.liusp.roommv.controller;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jackyliu on 2015/3/10.
 */
public class LockCompition {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private AtomicInteger value = new AtomicInteger(0);

    public void set(int value) {
            this.value.getAndSet(value);
    }

    public int get() throws InterruptedException {
        return this.value.get();
    }
}
