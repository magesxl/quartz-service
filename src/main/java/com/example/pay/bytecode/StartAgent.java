package com.example.pay.bytecode;

import java.lang.instrument.Instrumentation;

public class StartAgent {

    public static void premain(String args, Instrumentation instrumentation){
        //代理程序入口函数
        System.out.println("agent开始");
        //添加字节码转换
        instrumentation.addTransformer(new PrintTimeTransformer());
        System.out.println("agent结束");
    }
}
