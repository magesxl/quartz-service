package com.example.pay.service.impl;



import com.example.pay.dao.JobAndTriggerDao;
import com.example.pay.model.JobAndTrigger;
import com.example.pay.page.PageBean;
import com.example.pay.page.PageParam;
import com.example.pay.service.JobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public  class JobAndTriggerServiceImpl  implements JobAndTriggerService {

    @Autowired
    private JobAndTriggerDao jobAndTriggerDao;


    @Override
    public PageBean<JobAndTrigger> getJobAndTriggerByPage(PageParam pageParam, Map<String,Object> paramMap){
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        // 统计总记录数
        Long totalCount = jobAndTriggerDao.listPageCount();
        // 校验当前页数
        int currentPage = PageBean.checkCurrentPage(totalCount.intValue(), pageParam.getNumPerPage(), pageParam.getCurrentPage());
        pageParam.setCurrentPage(currentPage); // 为当前页重新设值
        // 校验页面输入的每页记录数numPerPage是否合法
        int numPerPage = PageBean.checkNumPerPage(pageParam.getNumPerPage()); // 校验每页记录数
        pageParam.setNumPerPage(numPerPage); // 重新设值

        // 根据页面传来的分页参数构造SQL分页参数
        paramMap.put("pageFirst", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("pageSize", pageParam.getNumPerPage());
        paramMap.put("startRowNum", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("endRowNum", pageParam.getCurrentPage() * pageParam.getNumPerPage());

        // 获取分页数据集
        List<JobAndTrigger> list = jobAndTriggerDao.listPage(paramMap);

        return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list);

    }
}
