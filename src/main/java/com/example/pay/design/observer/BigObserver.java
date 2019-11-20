package com.example.pay.design.observer;

public class BigObserver extends Observer {
    @Override
    protected void update() {
        System.out.println("我是大写" + subject.getState());
    }

    public BigObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
}
