package com.example.pay.test.java7;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/*

 */
public class CastTest {
    public static void main(String[] args) {
        switchF();
        numberF();
        objectF();
        randomF();
    }


    /*
        改进了switch分支语句，允许switch语句中的控制表达式为java.lang.String类型。
     */
    public static void switchF() {
        String a = "234";
        switch (a) {
            case "123456":
                System.out.println("1");
                break;
            default:
                System.out.println("2");
                break;
        }
    }

    /*
    数值中使用下画线分隔  防止大数出错
     */
    public static void numberF() {
        int a = 234_24_66_77;
        System.out.println(a);
    }

    /*
        Objects工具类
        Objects类提供的toString(Object o)方法，就不会引发空指针异常，当o为null 时，程序将返回一个“null”字符串
     */
    public static void objectF() {
        List list = null;
        System.out.println(Objects.toString(list));
    }

    public static void randomF(){
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int sum = threadLocalRandom.nextInt();
        System.out.println(Objects.toString(sum));
    }
}
