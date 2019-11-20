package com.example.pay.design.observer;

public class BinaryObserver extends Observer {
    @Override
    protected void update() {
        System.out.println("我是二进制" + Integer.toBinaryString(subject.getState()));
    }

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }


}
