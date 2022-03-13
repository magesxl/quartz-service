//package com.example.pay.job.impl;
//
//
//import com.example.pay.model.User;
//import com.example.pay.service.UserService;
//import org.joda.time.DateTime;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class HelloJob extends AbstractUniqueJob {
//    private static final Logger logger = LoggerFactory.getLogger(HelloJob.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void doExecute(JobExecutionContext context) {
//        //获取队列
//        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(20);//队列大小
//        //创建自定义线程池
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,20,
//                TimeUnit.SECONDS,blockingQueue);
//        logger.info("线程池活跃线程："+threadPoolExecutor.getActiveCount());
//
//
//        ThreadPoolMonitor monitor = new ThreadPoolMonitor(threadPoolExecutor);
//        monitor.start();
//        //查询数据库数据   处理完成数据后更新任务库
//        List<User>  list = userService.findUserInfo();
//        AtomicInteger successQuantity = new AtomicInteger(0);
//        //CountDownLatch的计数器只能使用一次 (计数器不能被重置)
//        CountDownLatch countDownLatch = new CountDownLatch(list.size());
//        for (User user:list) {
//            user.setName("李四");
//            if(user.getId()==6){
//                user.setName("大不列颠");
//            }
//            //获取成功更新的数量
//            threadPoolExecutor.execute(()->successQuantity.addAndGet(updateUserInfo(user,countDownLatch)));
//            logger.info("线程池活跃线程："+threadPoolExecutor.getActiveCount());
//            logger.info("当前线程名字为{}", Thread.currentThread().getName());
//        }
//        try {
//            //countDownLatch.await();
//            countDownLatch.await(5,TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        logger.info("本轮结束 成功发起{}条,失败{}条", successQuantity, list.size() - successQuantity.get());
////        JobDetail jobDetail = jobExecutionContext.getJobDetail();
////        JobDataMap jobDataMap = jobDetail.getJobDataMap();
////        Gson gson = new Gson();
////        logger.info(gson.toJson(jobDataMap));
////        logger.info(jobDataMap.toString());
////        logger.info("--你好");
//    }
//
//    @Override
//    protected Logger getLogger() {
//        return logger;
//    }
//
//    private int updateUserInfo(User user, CountDownLatch latch){
//        //一般来讲，我会尽量避免使用系统时钟来初始化应用程序的实际，而是倾向于外部化设置应用程序代码使用的系统时间。
//        long start = new DateTime().getMillis();
//        int i = userService.update(user);
//        long end = new DateTime().getMillis();
//        logger.info("用户id{}，消耗时间{}毫秒",user.getId(),end-start);
//        latch.countDown();
//        return  i;
//    }
//}
