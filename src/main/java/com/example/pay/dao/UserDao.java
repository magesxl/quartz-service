package com.example.pay.dao;



import com.example.pay.model.User;

import java.util.List;

public interface UserDao {
    List<User> queryUser();
    int update(User user);
}
