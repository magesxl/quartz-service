package com.example.pay.design.observer;

public class HexObserver extends Observer {
    @Override
    protected void update() {
        System.out.println("我是16进制" + Integer.toHexString(this.subject.getState()));
    }

    public HexObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
}
