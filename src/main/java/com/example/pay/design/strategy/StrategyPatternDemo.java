package com.example.pay.design.strategy;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        int end = context.executeStrategy(2, 1);
        System.out.println(end);
        context = new Context(new OperationSubstract());
        System.out.println(context.executeStrategy(2,1));
    }
}
