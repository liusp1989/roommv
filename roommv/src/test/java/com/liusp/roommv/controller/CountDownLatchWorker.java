package com.liusp.roommv.controller;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jackyliu on 2015/3/5.
 */
public class CountDownLatchWorker implements  Runnable {
    private CountDownLatch startLatch;
    private  CountDownLatch endLatch;

    public CountDownLatchWorker(CountDownLatch startLatch, CountDownLatch endLatch) {
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
        System.out.println("初始化开始");
        endLatch.countDown();
    }
}
