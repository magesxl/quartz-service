package com.example.pay.test.java7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaFuture {
    public static void main(String[] args){
        //使用() -> {} 替代匿名类
        Thread t1 = new Thread(()->{
            List<Integer> list = new ArrayList<>();
            list.add(3);
            list.add(4);
            list.add(5);
            list.add(1);
            //list.forEach(n->System.out.println(n));
            System.out.println(list.stream().findFirst());
            list.stream().filter(n->n>2).map(n->n+2).forEach(System.out::println);
            /*
            第一种方法引用是构造方法引用，语法是：Class::new ，对于泛型来说语法是：Class<T >::new，请注意构造方法没有参数:
1
final Car car = Car.create( Car::new );
2
final List< Car > cars = Arrays.asList( car );
第二种方法引用是静态方法引用，语法是：Class::static_method请注意这个静态方法只支持一个类型为Car的参数。

1
cars.forEach( Car::collide );
第三种方法引用是类实例的方法引用，语法是：Class::method请注意方法没有参数。

1
cars.forEach( Car::repair );
最后一种方法引用是引用特殊类的方法，语法是：instance::method，请注意只接受Car类型的一个参数。

1
final Car police = Car.create( Car::new );
2
cars.forEach( police::follow );

             */
            list.forEach(System.out::println);
        },"chengain1");
        t1.start();
        System.out.println(t1.getName());

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((key, value) -> System.out.println(key+value));

    }

}
