//package com.example.pay.job.impl;
//
//
//import com.example.pay.job.BaseJob;
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
//
//public class Test1 implements BaseJob {
//    private static final Logger logger = LoggerFactory.getLogger(Test1.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//
//        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(20);
//        /*
//        ThreadPoolExecutor类。它有一个内部的线程池和提供允许你提交两种任务给线程池执行的方法。这些任务是：
//        Runnable接口，实现没有返回结果的任务
//        Callable接口，实现返回结果的任务
//         */
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,10,30,
//                TimeUnit.SECONDS,blockingQueue,new ThreadPoolExecutor.CallerRunsPolicy());
//        List<User> list= userService.findUserInfo();
//        /*
//        让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
//        CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，
//        每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
//        CountDownLatch和CyclicBarrier的区别
//(01) CountDownLatch的作用是允许1或N个线程等待其他线程完成执行；而CyclicBarrier则是允许N个线程相互等待。
//(02) CountDownLatch的计数器无法被重置；CyclicBarrier的计数器可以被重置后使用，因此它被称为是循环的barrier。
//         */
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(list.size());
//        logger.info("要屏障线程数量："+list.size());
//        AtomicInteger successQuantity = new AtomicInteger(0);
//        for(User user:list){
//            user.setName("张三丰");
//            threadPoolExecutor.execute(() -> successQuantity.addAndGet(updateUserInfo(user, cyclicBarrier)));
//            logger.info("线程池存活数量："+threadPoolExecutor.getActiveCount());
//            logger.info("拦截线程数量："+cyclicBarrier.getNumberWaiting());
//            logger.info("线程名字"+Thread.currentThread().getName());
//        }
//        logger.info("本轮结束 成功发起{}条,失败{}条", successQuantity, list.size() - successQuantity.get());
//
//    }
//
//
//    private int updateUserInfo(User user,CyclicBarrier cyclicBarrier) {
//        long start = new DateTime().getMillis();
//        int i = userService.update(user);
//        long end = new DateTime().getMillis();
//        logger.info("用户id:{}，消耗时间{}毫秒",user.getId(),end-start);
//        try {
//            cyclicBarrier.await();
//        } catch (InterruptedException | BrokenBarrierException e) {
//            e.printStackTrace();
//        }
//        return  i;
//    }
//}
