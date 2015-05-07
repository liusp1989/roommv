package com.liusp.roommv.controller;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jackyliu on 2015/3/5.
 */
public class QueueTest {
    public static  void  main(String[] args){
        ReentrantLock lock = new ReentrantLock();
        ConcurrentLinkedDeque<String> strings = new ConcurrentLinkedDeque<String>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        strings.add("f");
        while (true){
            lock.lock();
            if(strings.isEmpty()){
                System.out.println("消费完");
                break;
            }else {
                System.out.println(strings.poll());
            }
        }
        System.out.println("hehe");
    }
}
