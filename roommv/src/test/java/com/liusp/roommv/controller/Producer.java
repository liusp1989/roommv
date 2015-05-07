package com.liusp.roommv.controller;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by jackyliu on 2015/3/3.
 */
public class Producer implements  Runnable{
    private ArrayBlockingQueue<String> products ;

    public Producer(ArrayBlockingQueue<String> products) {
        this.products = products;
    }
    public void product(){
        int i=0;
        while(true) {
            try {
                products.put(String.valueOf(i));
                System.out.println(products.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    @Override
    public void run() {
        this.product();
    }
}
