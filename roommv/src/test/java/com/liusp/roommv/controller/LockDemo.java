package com.liusp.roommv.controller;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackyliu on 2015/3/6.
 */
public class LockDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int THREAD_COUNT = 1000;
        int INCREASE_COUNT = 1000;

        CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT+1);
        ExecutorService service1 = Executors.newFixedThreadPool(THREAD_COUNT);
        ExecutorService service2 = Executors.newFixedThreadPool(THREAD_COUNT);
        ExecutorService service3 = Executors.newFixedThreadPool(THREAD_COUNT);
        LockInterface lockInterfaceLockUnfair = new LockImpl();
        LockInterface lockInterfaceFair = new FairLockImpl();
        LockInterface lockInterfaceSyn = new SynImpl();
        Long startDate = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            service1.execute(new LockDemo().new Task(lockInterfaceLockUnfair, INCREASE_COUNT, barrier));
        }
        barrier.await();
        Long unfairDate = System.currentTimeMillis();
        System.out.println("unfair耗时:"+(unfairDate-startDate)+";数值 :"+lockInterfaceLockUnfair.getValue());
        for (int i = 0; i < THREAD_COUNT; i++) {
            service2.execute(new LockDemo().new Task(lockInterfaceFair, INCREASE_COUNT, barrier));
        }
        barrier.await();
        Long fairDate = System.currentTimeMillis();
        System.out.println("fair耗时:"+(fairDate-unfairDate)+";数值 :"+lockInterfaceFair.getValue());
        for (int i = 0; i < THREAD_COUNT; i++) {
            service3.execute(new LockDemo().new Task(lockInterfaceSyn, INCREASE_COUNT, barrier));
        }
        barrier.await();
        System.out.println("syn耗时:"+(System.currentTimeMillis()-fairDate)+";数值 :"+lockInterfaceSyn.getValue());
        service1.shutdown();
        service2.shutdown();
        service3.shutdown();
    }

    private class Task implements Runnable {
        private LockInterface lockInterface;
        private CyclicBarrier barrier;
        private int increaceCount;

        public Task(LockInterface lockInterface, int increaceCount, CyclicBarrier barrier) {
            this.lockInterface = lockInterface;
            this.barrier = barrier;
            this.increaceCount = increaceCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < increaceCount;i++) {
                lockInterface.increace();
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
