package com.liusp.roommv.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jackyliu on 2015/3/6.
 */
class FairLockImpl implements  LockInterface {
    private Lock lock = new ReentrantLock(true);
    private volatile int i;
    @Override
    public void increace() {
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getValue() {
        return i;
    }
}
