package com.liusp.roommv.controller;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by jackyliu on 2015/3/6.
 */
public class ExchangerDemo {
    public  static void  main(String[] args){
        Exchanger<List<String>> exchanger = new Exchanger<>();
        new Thread(new ExchangeWorker1(exchanger)).start();
        new Thread(new ExchangeWorker2(exchanger)).start();
    }
}
