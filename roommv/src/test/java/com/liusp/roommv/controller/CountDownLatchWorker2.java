package com.liusp.roommv.controller;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jackyliu on 2015/3/6.
 */
public class CountDownLatchWorker2 implements  Runnable {
    private CountDownLatch startLatch;
    private  CountDownLatch endLatch;

    public CountDownLatchWorker2(CountDownLatch startLatch, CountDownLatch endLatch) {
        this.startLatch = startLatch;
        this.endLatch = endLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("工作开始");
        endLatch.countDown();
    }
}
