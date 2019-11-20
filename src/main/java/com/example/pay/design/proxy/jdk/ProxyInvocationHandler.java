package com.example.pay.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {
    //真实对象
    private Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是1");
        System.out.println(method.getModifiers());
        System.out.println(method.getModifiers()&1033);
        Object object = method.invoke(target, args);
        System.out.println("我是2");
        return object;
    }
}
