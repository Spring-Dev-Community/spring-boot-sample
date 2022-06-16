package com.relive.quartz.memory;

import com.relive.quartz.SampleJob;
import com.relive.quartz.listener.SchedulerListenerBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Quartz 内存存储配置类
 *
 * @author: ReLive
 * @date: 2022/6/14 3:10 下午
 */
@Configuration
@ConditionalOnProperty(
        prefix = "spring.quartz",
        name = {"job-store-type"},
        havingValue = "memory"
)
public class MemoryQuartzConfig {

    /**
     * 注册全局Scheduler Listener
     *
     * @return
     */
    @Bean
    public SchedulerListenerBuilder schedulerListenerBuilder() {
        return new SchedulerListenerBuilder();
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(SampleJob.class)
                .storeDurably() //持久存储
                .withIdentity("quartz_Job_Detail") //设置job标识
                .withDescription("Invoke Sample Job service...") //设置job描述
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("quartz_Trigger")
                .withDescription("Sample trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(3))
                .build();
    }
}
