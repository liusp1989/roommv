package com.liusp.roommv.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackyliu on 2015/3/5.
 */
public class  CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startLatch= new CountDownLatch(1);
        CountDownLatch endlatch = new CountDownLatch(10);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            service.submit(new CountDownLatchWorker(startLatch,endlatch));
        }
        for(int i=0;i<5;i++){
            service.submit(new CountDownLatchWorker2(startLatch,endlatch));
        }
        System.out.println("暂停所有线程");
        startLatch.countDown();
        System.out.println("开始执行所有线程");
        endlatch.await();
        System.out.println("开始执行主线程");
        service.shutdown();
    }
}
