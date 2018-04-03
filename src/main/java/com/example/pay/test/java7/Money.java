package com.example.pay.test.java7;


import java.text.NumberFormat;

public class Money {
    public static void  main(String[] args){
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println(numberFormat.format(10000000000000L));
        System.out.println(numberFormat.format(2333444422.6345));
    }
}
