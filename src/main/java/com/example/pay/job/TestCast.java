package com.example.pay.job;


import com.example.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCast {
    @Autowired
    private UserService userService;

    public  void test(){
        System.out.println("你好"+userService);

        System.out.println("你好"+userService.findUserInfo());
    }

    public static void main(String[] args){
        TestCast testCast = new TestCast();
        testCast.test();
    }


}
