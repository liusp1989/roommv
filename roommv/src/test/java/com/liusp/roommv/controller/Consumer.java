package com.liusp.roommv.controller;

import org.junit.runners.ParentRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by jackyliu on 2015/3/3.
 */
public class Consumer implements Runnable{
    private ArrayBlockingQueue<String> products ;

    public Consumer(ArrayBlockingQueue<String> products) {
        this.products = products;
    }
    public void consume(){
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(products.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        this.consume();
    }
}
