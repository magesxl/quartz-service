package com.example.pay.design.proxy.jdk;

public class RealImage implements Image {

    @Override
    public void display() {
        System.out.println("真实操作处理类");
    }
}
