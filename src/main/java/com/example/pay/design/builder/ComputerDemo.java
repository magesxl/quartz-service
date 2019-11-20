package com.example.pay.design.builder;

public class ComputerDemo {
    public static void main(String[] args) {
        Computer com = Computer.builder().cpu("1").ram("2").build();
        System.out.println(com);
    }
}
