package com.liusp.roommv.controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by jackyliu on 2015/3/3.
 */
public class ResourceControl {
    private Semaphore semaphore = new Semaphore(3);
    private String[] strings = new String[]{"A","B","C","D","E"};
    public String get(){
        Random random = new Random(System.currentTimeMillis());
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  strings[random.nextInt(5)];
    }
    public  void release(){
        semaphore.release();
    }

}
