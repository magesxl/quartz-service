package com.example.pay.javassist;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Hello {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("Hello, World!");
        Class classA = Class.forName("com.example.pay.model.City");
        Class[] classesType = new Class[]{Long.class,Long.class};
        Constructor constructor1 = classA.getConstructor(classesType);
        System.out.println("开始为"+constructor1.isVarArgs());

        Constructor[] constructors = classA.getConstructors();
        for(Constructor constructor:constructors){
            System.out.print(constructor.getParameterCount());
            System.out.println(constructor.getName());
        }

        for (int i = 0; i < args.length; i++) {
            String result = buildString(Integer.parseInt(args[i]));
            System.out.println("Constructed string of length " +
                    result.length());
        }

    }

    private static String buildString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (char)(i%26 + 'a');
        }
        return result;
    }
}
