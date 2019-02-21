package com.example.pay.design.proxy.jdk;

import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler(new RealImage());
        Image image = (Image) Proxy.newProxyInstance(Image.class.getClassLoader(),
                new Class[]{Image.class}, proxyInvocationHandler);
        image.display();
    }
}
