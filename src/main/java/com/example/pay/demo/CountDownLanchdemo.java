package com.example.pay.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


public class CountDownLanchdemo {
    private CountDownLatch countDownLatch;

    private int start = 0;

    private int mid = 5;

    private int end = 10;

    AtomicInteger atomicInteger1 = new AtomicInteger();

    AtomicInteger atomicInteger2 = new AtomicInteger();

    AtomicInteger atomicInteger3 = new AtomicInteger();

    private int add(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }

    private int sum(int a, int b) {
        return a + b;
    }

    public void test() {
        countDownLatch = new CountDownLatch(2);  //两个线程相加后 执行第三个线程
        Thread t1 = new Thread(() -> {
            System.out.println("线程1执行了");
            countDownLatch.countDown();
            int a = add(start, mid);
            System.out.println(Thread.currentThread().getName() +
                    " : calculate ans: " + a);
        }, "线程1");
        Thread t2 = new Thread(() -> {
            System.out.println("线程2执行了");
            countDownLatch.countDown();
            int a = add(mid, end);
            System.out.println(Thread.currentThread().getName() +
                    " : calculate ans: " + a);
        }, "线程2");
        Thread t3 = new Thread(() -> {
            System.out.println("线程3执行了");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a = sum(4, 5);
            System.out.println(Thread.currentThread().getName() +
                    " : calculate ans: " + a);
        }, "线程3");
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        CountDownLanchdemo countDownLanchdemo = new CountDownLanchdemo();
        countDownLanchdemo.test();

    }
}
