package com.liusp.roommv.controller;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackyliu on 2015/3/4.
 */
public class BarrierDemo {

   public static  void main(String[] args){
       CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
           @Override
           public void run() {
               System.out.println("线程等起");
           }
       });
       ExecutorService service = Executors.newFixedThreadPool(5);
       for(int i=0;i<5;i++){
           service.submit(new BarrierWorker(barrier));
       }
       service.shutdown();
   }
}
