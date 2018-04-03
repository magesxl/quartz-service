package com.example.pay.job.impl;


import com.example.pay.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("1");
    }
}
