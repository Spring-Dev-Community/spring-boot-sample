package com.relive.quartz.listener;

import lombok.SneakyThrows;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

/**
 * 注册Scheduler Listener
 *
 * @author: ReLive
 * @date: 2022/6/15 3:49 下午
 */
public class SchedulerListenerBuilder implements ApplicationContextAware {


    @SneakyThrows
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Scheduler scheduler = applicationContext.getBean(Scheduler.class);
        scheduler.getListenerManager().addJobListener(createJobListener(), allJobs());
    }


    private JobListener createJobListener() {
        return new GlobalJobListener();
    }
}
