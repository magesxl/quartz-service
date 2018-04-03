package com.example.pay.service;



import com.example.pay.model.JobAndTrigger;
import com.example.pay.page.PageBean;
import com.example.pay.page.PageParam;

import java.util.Map;

public interface JobAndTriggerService {
    public PageBean<JobAndTrigger> getJobAndTriggerByPage(PageParam pageParam, Map<String, Object> paramMap);
}
