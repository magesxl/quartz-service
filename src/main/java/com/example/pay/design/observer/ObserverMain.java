package com.example.pay.design.observer;

public class ObserverMain {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new HexObserver(subject);
        new BigObserver(subject);
        subject.setState(10);
        subject.setState(11);
        subject.setState(19);
        System.out.println(111122222);
    }
}
