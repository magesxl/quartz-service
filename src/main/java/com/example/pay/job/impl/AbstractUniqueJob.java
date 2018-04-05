package com.example.pay.job.impl;

import com.example.pay.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 增强:前一个job未执行完时,后一个job取消
 */
public abstract class AbstractUniqueJob implements BaseJob {

    private AtomicInteger active = new AtomicInteger(1);//同一类job只允许一个活跃

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        if (active.get() == 0) {
            getLogger().info("上一轮Job尚未完成,本次取消");
            return;
        }
        active.decrementAndGet();
        getLogger().info("{} 开始执行...", context.getJobDetail().getKey());
        long now = System.currentTimeMillis();
        try {
            doExecute(context);
        } catch (Exception e) {
            getLogger().error("本次Job运行异常,退出,等待下次...", e);
        }

        getLogger().info("{} 执行结束, 耗时 {}", context.getJobDetail().getKey(), System.currentTimeMillis() - now);
        active.incrementAndGet();//填充
    }

    protected abstract void doExecute(JobExecutionContext context);

    protected abstract Logger getLogger();

    class ThreadPoolMonitor extends Thread {
        ThreadPoolExecutor executor;
        private volatile boolean finished = false;

        public ThreadPoolMonitor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            while (!finished) {
                getLogger().info(executor.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            getLogger().info("监控线程结束");
        }

        public void finish() {
            this.finished = true;
            this.interrupt();//run可能正在阻塞,线程不能立刻检测到条件变量,所以调动中断,提高即时性
        }
    }
}
