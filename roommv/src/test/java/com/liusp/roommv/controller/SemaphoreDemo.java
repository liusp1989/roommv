package com.liusp.roommv.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackyliu on 2015/3/3.
 */
public class SemaphoreDemo implements  Runnable{
    private ResourceControl resourceControl;

    public SemaphoreDemo(ResourceControl resourceControl) {
        this.resourceControl = resourceControl;
    }

    public static  void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(10);
        ResourceControl re = new ResourceControl();
        while (true){
            service.execute(new SemaphoreDemo(re));
        }
    }


    @Override
    public void run() {
        System.out.println(resourceControl.get());
        resourceControl.release();
    }
}
