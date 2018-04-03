package com.example.pay.test.java7;

import java.time.Clock;

public class DateTest {
    public static void main(String[] args){
        long clock = Clock.systemDefaultZone().millis();//获取当前毫秒数
    }
}
