package com.example.pay.design.singleton;

public class SingletonDemo {
    public static void main(String[] args) {
        String instance = SingletonA.INSTANCE.getClass().getSimpleName();
        SingletonA singletonA = SingletonA.INSTANCE;
        System.out.println(instance);
        System.out.println(singletonA);
        SingletonA.INSTANCE.whateverMethod();
    }
}
