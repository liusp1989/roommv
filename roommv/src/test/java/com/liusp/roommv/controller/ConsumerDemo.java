package com.liusp.roommv.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jackyliu on 2015/3/6.
 */
public class ConsumerDemo {
    public  static  void main(String[] args){
        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(10000,false);
        for(int i=0;i<10000;i++) {
            strings.offer(null);
        }
        System.out.println(strings.isEmpty());
        for(int i=0;i<60;i++){
            new Thread(new ConsumerDemo().new Consumer(strings)).start();
        }

    }
    private class Consumer implements  Runnable {
        private Lock lock = new ReentrantLock(false);
        private ArrayBlockingQueue<String> strings;

        public Consumer(ArrayBlockingQueue<String> strings) {
            this.strings = strings;
        }

        @Override
        public  void  run() {
                while (true) {
                    String s= strings.poll();
                    if (s==null) {
                        break;
                    } else {
                           if(null==s){
                               System.out.println(s);
                               System.out.println(strings.isEmpty());
                               throw new RuntimeException("aaaa");
                           }

                    }
                }
        }
    }
}
