package com.example.pay.service.impl;


import com.example.pay.dao.BaseRepository;
import com.example.pay.model.Demo;
import com.example.pay.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl extends BaseRepository implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public List<Demo> findDemo() {
        List<Demo> list = sqlSessionTemplate.selectList("DemoDao.queryUser");
        logger.info("数据为："+list);
        return list;
    }


}
