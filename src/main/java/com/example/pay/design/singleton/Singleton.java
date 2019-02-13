package com.example.pay.design.singleton;


/**
 * 双重检测
 */
public class Singleton {
    /**
     * 注意此处使用的关键字 volatile，
     * 被volatile修饰的变量的值，将不会被本地线程缓存，
     * 所有对该变量的读写都是直接操作共享内存，从而确保多个线程能正确的处理该变量。
     */
    private volatile Singleton singleton;

    private Singleton() {

    }

    public Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
