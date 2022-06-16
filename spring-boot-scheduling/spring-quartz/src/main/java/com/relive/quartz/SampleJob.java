package com.relive.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.util.CollectionUtils;

/**
 * @author: ReLive
 * @date: 2022/6/14 10:28 上午
 */
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        if (CollectionUtils.isEmpty(dataMap)) {
            System.out.println("sample job...");
        } else {
            System.out.println("sample job...,username:" + dataMap.get("username"));
        }
        // doSomething...
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
