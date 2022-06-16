package com.relive.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 服务启动向Scheduler添加job
 *
 * @author: ReLive
 * @date: 2022/6/14 3:32 下午
 */
@Component
public class JobEvent implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                .storeDurably(false) //job是非持久的，当没有活跃的trigger与之关联的时候，会被自动地从scheduler中删除。非持久的job的生命期是由trigger的存在与否决定的；
                .requestRecovery(true) //job是可恢复的，并且在其执行的时候，scheduler发生硬关闭（hard shutdown)（比如运行的进程崩溃了，或者关机了），则当scheduler重新启动的时候，该job会被重新执行。此时，该job的JobExecutionContext.isRecovering() 返回true
                .withIdentity("quartz_jodDetail_event")
                .usingJobData("username", "admin")
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("quartz_Trigger_event")
                .withDescription("Sample trigger" + System.nanoTime())
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(60))
                .startNow()
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        Thread.sleep(1000 * 10);

        //删除job
        JobKey jobKey = new JobKey("quartz_jodDetail_event");
        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }

        TriggerKey triggerKey = new TriggerKey("quartz_Trigger_event");
        if (scheduler.checkExists(triggerKey)) {
            scheduler.unscheduleJob(triggerKey);
        }
    }
}
