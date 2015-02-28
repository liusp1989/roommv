package com.liusp.roommv.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackyliu on 2015/3/3.
 */
public class PCMain {
    public static  void main(String[] args){
        ArrayBlockingQueue<String> products =new ArrayBlockingQueue<String>(50);
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(new Producer(products));
        service.execute(new Consumer(products));
    }
}
