package com.example.pay.service;


import com.example.pay.model.User;

import java.util.List;


public interface UserService {
    List<User> findUserInfo();

    int update(User user);
}
