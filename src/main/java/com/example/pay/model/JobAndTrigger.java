package com.example.pay.model;

import lombok.Data;

/**
 * 作业详情类
 */

@Data
public class JobAndTrigger {

    private String  jobName;

    private String  jobGroup;

    private String  jobClassName;

    private String  triggerName;

    private String  triggerGroup;

    private String  cronExpression;

    private String  triggerState;

    private String  timeZoneId;

    private String  descrption;


}
