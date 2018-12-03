package com.example.pay.test.java7;


import java.text.NumberFormat;

public class Money {
    public static void  main(String[] args){
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println(numberFormat.format(1000L));
        System.out.println(numberFormat.format(2322.6345));
    }
}
