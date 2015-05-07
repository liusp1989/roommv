package com.liusp.roommv.controller;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by jackyliu on 2015/3/4.
 *
 */
public class BarrierWorker implements  Runnable  {
    private CyclicBarrier barrier;

    public BarrierWorker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"arrive 1");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + "arrive 2");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + "arrive 3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
