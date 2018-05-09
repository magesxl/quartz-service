package com.example.pay.dao;



import com.example.pay.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> queryUser();
    int update(User user);

    void batchUpdateOrderByMerId(@Param("list") List<User> users);

    int batchUpdateByPaDefenceId(Map<String,Object> params);
}
