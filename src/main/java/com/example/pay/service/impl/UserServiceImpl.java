package com.example.pay.service.impl;


import com.example.pay.dao.UserDao;
import com.example.pay.model.User;
import com.example.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public List<User> findUserInfo() {
        return userDao.queryUser();
    }

    @Override
    public int update(User user) {
       return userDao.update(user);
    }
}
