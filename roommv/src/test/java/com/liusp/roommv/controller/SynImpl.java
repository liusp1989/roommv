package com.liusp.roommv.controller;

/**
 * Created by jackyliu on 2015/3/6.
 */
public class SynImpl implements  LockInterface {
    private volatile int i;
    @Override
    public synchronized void increace() {
        i++;
    }

    @Override
    public int getValue() {
        return i;
    }
}
