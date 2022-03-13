//package com.example.pay.listener;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.JobListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class MyJobListener implements JobListener {
//
//    private final static Logger logger= LoggerFactory.getLogger(MyJobListener.class);
//
//
//    @Override
//    public String getName() {
//        return "MyJobListener";
//    }
//
//    @Override
//    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
//        System.out.println("======");
//    }
//
//    /*
//    任务被触发前触发
//     */
//    @Override
//    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
//        System.out.println("-------");
//    }
//
//    /*
//    任务调度完成后触发
//     */
//    @Override
//    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
//        System.out.println("+++++++");
//    }
//}
