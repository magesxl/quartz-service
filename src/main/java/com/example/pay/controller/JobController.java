package com.example.pay.controller;


import com.example.pay.job.BaseJob;
import com.example.pay.listener.MyJobListener;
import com.example.pay.model.JobAndTrigger;
import com.example.pay.page.PageBean;
import com.example.pay.page.PageParam;
import com.example.pay.service.JobAndTriggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.quartz.TriggerKey.triggerKey;

@Api(value = "工作列表", tags = "处理工作内容")
@RestController
@RequestMapping("/job")
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobAndTriggerService jobAndTriggerService;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @ApiOperation(value = "查询任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", paramType = "form", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", paramType = "form", dataType = "int")})
    @RequestMapping(value = "/findjob", method = RequestMethod.GET)
    public Map<String, Object> findJob(
            @RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "pageSize") int pageSize) {

        Map<String, Object> map = new HashMap<>();
        PageParam pageParam = new PageParam();
        pageParam.setCurrentPage(pageNum);
        pageParam.setNumPerPage(pageSize);
        PageBean<JobAndTrigger> jobAndTriggerPageBean = jobAndTriggerService.getJobAndTriggerByPage(pageParam, map);
        Map<String, Object> result = new HashMap<>();
        result.put("jobAndTrigger", jobAndTriggerPageBean.getRecordList());
        result.put("number", jobAndTriggerPageBean.getTotalCount());
        return result;
    }

    /*
    一、表信息解析：
1.1.qrtz_blob_triggers : 以Blob 类型存储的触发器。
1.2.qrtz_calendars：存放日历信息， quartz可配置一个日历来指定一个时间范围。
1.3.qrtz_cron_triggers：存放cron类型的触发器。
1.4.qrtz_fired_triggers：存放已触发的触发器。
1.5.qrtz_job_details：存放一个jobDetail信息。
1.6.qrtz_job_listeners：job**监听器**。
1.7.qrtz_locks： 存储程序的悲观锁的信息(假如使用了悲观锁)。
1.8.qrtz_paused_trigger_graps：存放暂停掉的触发器。
1.9.qrtz_scheduler_state：调度器状态。
1.10.qrtz_simple_triggers：简单触发器的信息。
1.11.qrtz_trigger_listeners：触发器监听器。
1.12.qrtz_triggers：触发器的基本信息。

二、Quartz的触发时间的配置的三种方式：

2.1.cron 方式：采用cronExpression表达式配置时间。
2.2.simple 方式：和JavaTimer差不多，可以指定一个开始时间和结束时间外加一个循环时间。
2.3.calendars 方式：可以和cron配合使用，用cron表达式指定一个触发时间规律，用calendar指定一个范围。

注意：cron方式需要用到的4张数据表：
qrtz_triggers，qrtz_cron_triggers，qrtz_fired_triggers，qrtz_job_details。
     */
    @RequestMapping(value = "/addjob", method = RequestMethod.POST)
    public void addjob(
            @RequestParam(value = "jobName") String jobName,
            @RequestParam(value = "jobClassName") String jobClassName,
            @RequestParam(value = "jobGroupName") String jobGroupName,
            @RequestParam(value = "cronExpression") String cronExpression,
            @RequestParam(value = "description") String description) throws Exception {
        addJob(jobName, description, jobClassName, jobGroupName, cronExpression);
    }

    public void addJob(String jobName, String description, String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        // 通过SchedulerFactory获取一个调度器实例
        Scheduler sched = schedulerFactoryBean.getScheduler(); //sf.getScheduler();

        // 启动调度器
        sched.start();

        //构建job信息
        //任务名，任务组，任务执行类
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)  触发器时间设定
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger(触发器)
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .withSchedule(scheduleBuilder).withDescription(description).build();

        //第四步：关联监听器
        JobListener listener = new MyJobListener();
        sched.getListenerManager().addJobListener(listener);


        try {
            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }

    @RequestMapping(value = "/pausejob", method = RequestMethod.POST)
    public void pauseJob(@RequestParam(value = "jobName") String jobName,
                         @RequestParam(value = "jobClassName") String jobClassName,
                         @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
    }


    @RequestMapping(value = "/deletejob", method = RequestMethod.POST)
    public void deleteJob(@RequestParam(value = "jobName") String jobName,
            @RequestParam(value = "jobClassName") String jobClassName,
                          @RequestParam(value = "jobGroupName") String jobGroupName) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        try {
            //暂停触发器
            scheduler.pauseTrigger(triggerKey(jobName, jobGroupName));
            //移除触发器
            scheduler.resumeTrigger(triggerKey(jobName, jobGroupName));
            //删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.getStackTrace();
            logger.error(e.getMessage());
        }
    }


    @RequestMapping(value = "/resumejob", method = RequestMethod.POST)
    public void resumeJob(@RequestParam(value = "jobName") String jobName,
            @RequestParam(value = "jobClassName") String jobClassName,
                          @RequestParam(value = "jobGroupName") String jobGroupName) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.getStackTrace();
            logger.error(e.getMessage());
        }


    }

    @RequestMapping(value = "/runjob", method = RequestMethod.POST)
    public void runJob(@RequestParam(value = "jobName") String jobName,
                          @RequestParam(value = "jobClassName") String jobClassName,
                          @RequestParam(value = "jobGroupName") String jobGroupName) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.triggerJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.getStackTrace();
            logger.error(e.getMessage());
        }


    }

    /*
    更新定时任务计划   只更新定时表达式
     */
    @RequestMapping(value = "/reschedulejob", method = RequestMethod.POST)
    public void updateJob(@RequestParam(value = "jobName") String jobName,
                          @RequestParam(value = "jobClassName") String jobClassName,
                          @RequestParam(value = "jobGroupName") String jobGroupName,
                          @RequestParam(value = "cronExpression") String cronExpression) {
        //获取调度器
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        try {
            //获取旧的触发器
            Trigger oldTrigger = scheduler.getTrigger(triggerKey(jobName, jobGroupName));

            TriggerBuilder triggerBuilder = oldTrigger.getTriggerBuilder();

            //表达式调度构建器(即任务执行的时间)  触发器时间设定
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);


            Trigger newTrigger = triggerBuilder.withIdentity(jobName, jobGroupName).withSchedule(scheduleBuilder).build();

            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
            logger.info("更新成功" + newTrigger);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }


    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }


}
