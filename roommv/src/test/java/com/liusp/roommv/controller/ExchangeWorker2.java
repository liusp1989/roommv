package com.liusp.roommv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;

/**
 * Created by jackyliu on 2015/3/6.
 */
public class ExchangeWorker2 implements  Runnable {
    private Exchanger<List<String>> listExchanger;
    private List<String> list = new CopyOnWriteArrayList<>();
    public ExchangeWorker2(Exchanger<List<String>> listExchanger) {
        this.listExchanger = listExchanger;
    }

    @Override
    public void run() {
        while (true) {
            list.add("哈哈");
            List<String> resultList = null;
            try {
                resultList = listExchanger.exchange(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(String s:resultList){
                System.out.println(s);
            }
        }
    }
}
