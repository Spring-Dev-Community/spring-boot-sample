package com.relive.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

/**
 * @author: ReLive
 * @date: 2022/6/15 3:26 下午
 */
@Slf4j
@Component
public class GlobalJobListener implements JobListener {
    @Override
    public String getName() {
        return "GlobalJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        log.info("Job即将执行的通知 " + jobExecutionContext.getJobDetail().getKey().getName());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        log.info("Job取消执行的通知 " + jobExecutionContext.getJobDetail().getKey().getName());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        log.info("Job完成执行的通知 " + jobExecutionContext.getJobDetail().getKey().getName());
    }
}
