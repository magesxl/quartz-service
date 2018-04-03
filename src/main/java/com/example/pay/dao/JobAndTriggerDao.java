package com.example.pay.dao;


import com.example.pay.model.JobAndTrigger;

import java.util.List;
import java.util.Map;


public interface JobAndTriggerDao{
    public List<JobAndTrigger> listPage(Map<String, Object> paramMap);
    public Long listPageCount();
}
