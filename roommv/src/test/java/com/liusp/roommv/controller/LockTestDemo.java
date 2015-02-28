package com.liusp.roommv.controller;

/**
 * Created by jackyliu on 2015/3/10.
 */
public class LockTestDemo {
    public static  void main(String[] args) throws InterruptedException {
        final LockCompition compition = new LockCompition();
       new Thread(new Runnable() {
            @Override
            public void run() {
                compition.set(23);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(compition.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
