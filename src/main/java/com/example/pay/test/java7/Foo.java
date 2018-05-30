package com.example.pay.test.java7;

public class Foo {
    //instance variable initializer
    String s = "abc";

    //constructor
    public Foo() {
        System.out.println("constructor called");
    }

    //static initializer
    //在java中使用static关键字声明的代码块。静态块用于初始化类，为类的属性初始化。每个静态代码块只会执行一次。在由于JVM在加载类时会执行静态代码块，所以静态代码块先于主方法执行
    //1 静态代码块不能存在于任何方法体内。2 静态代码块不能直接访问静态实例变量和实例方法，需要通过类的实例对象来访问。
    static {
        System.out.println("static initializer called");
    }

    //instance initializer
    //　直接在类中定义且没有加static关键字的代码块称为{}构造代码;
    /**
     *  构造代码块注意点：
     *　　1：java编译器编译java类时，会先将成员属性的声明放到类的前端
     *　　2：成员变量的初始化工作放到构造函数中
     *　　3：如果类中有构造代码块，java编译器在编译时会先将构造代码块中的代码移到构造函数中执行，构造函数中原有的代码最后执行
     *　  4：成员属性的初始化和构造代码块的执行顺序是根据原码中的位置执行
     */
    {
        System.out.println("instance initializer called");
    }

    //在函数中的代码块、作用：在方法中，如果要缩短变量的寿命，可以使用
    //方法中，某段代码之后，都不再使用某个变量（这个变量有可能是一个很大的Map集合，很占内存），则可以将其定义到局部代码块中，及时结束其生命周期，释放空间！

    public static void main(String[] args) {
        new Foo();
        new Foo();
        int sum = 0;
        a:
        for (int i = 0; i < 10; i++) {
            sum++;
            if(i==1){
                break a;
            }
        }
        System.out.println(sum);
    }


}
