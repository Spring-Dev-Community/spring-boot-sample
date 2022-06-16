package com.relive.quartz.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.relive.quartz.SampleJob;
import com.relive.quartz.listener.SchedulerListenerBuilder;
import com.zaxxer.hikari.HikariDataSource;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 配置JDBC JobStore
 *
 * @author: ReLive
 * @date: 2022/6/14 3:39 下午
 */
@Configuration
@ConditionalOnProperty(
        prefix = "spring.quartz",
        name = {"job-store-type"},
        havingValue = "jdbc"
)
public class JdbcQuartzConfig {

    /**
     * 注册全局Scheduler Listener
     *
     * @return
     */
    @Bean
    public SchedulerListenerBuilder schedulerListenerBuilder() {
        return new SchedulerListenerBuilder();
    }


    /**
     * 使用Quartz风格构建JobDetail和Trigger，无需指定数据源，使用默认数据源
     */
    @Configuration
    @ConditionalOnProperty(prefix = "quartz",
            name = "using",
            havingValue = "quartz")
    static class QuartzConfig {
        @Bean
        public JobDetail jobDetail() {
            return JobBuilder.newJob().ofType(SampleJob.class)
                    .storeDurably()
                    .withIdentity("qrtz_jdbc_Job_Detail")
                    .withDescription("Invoke Sample Job service...")
                    .build();
        }

        @Bean
        public Trigger trigger(JobDetail job) {
            return TriggerBuilder.newTrigger().forJob(job)
                    .withIdentity("Qrtz_jdbc_Trigger")
                    .withDescription("Sample trigger")
                    .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(1))
                    .build();
        }

    }

    /**
     * 使用Spring风格构建JobDetail和Trigger，无需指定数据源，使用默认数据源
     */
    @Configuration
    @ConditionalOnProperty(prefix = "quartz",
            name = "using",
            havingValue = "spring")
    @EnableAutoConfiguration
    static class SpringQuartzConfig {
        @Bean
        public JobDetailFactoryBean jobDetail() {
            JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
            jobDetailFactory.setJobClass(SampleJob.class);
            jobDetailFactory.setDescription("Invoke Sample Job service...");
            jobDetailFactory.setName("spring_quartz_jobDetail");
            jobDetailFactory.setDurability(true);
            return jobDetailFactory;
        }

        @Bean
        public SimpleTriggerFactoryBean trigger(JobDetail job) {
            SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
            trigger.setJobDetail(job);
            //设置重复间隔60s
            trigger.setRepeatInterval(1000 * 60);
            //设置重复次数无限重复
            trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
            return trigger;
        }
    }

    /**
     * 多数据源，quartz指定数据源
     */
    @Configuration
    @ConditionalOnProperty(prefix = "spring.profiles",
            name = "active",
            havingValue = "multi")
    static class MultipleDataSourceQuartzConfig {

        @Bean
        public JobDetail jobDetail() {
            return JobBuilder.newJob().ofType(SampleJob.class)
                    .storeDurably()
                    .withIdentity("qrtz_multi_datasource_Job_Detail")
                    .withDescription("Invoke Sample Job service...")
                    .build();
        }

        @Bean
        public Trigger trigger(JobDetail job) {
            return TriggerBuilder.newTrigger().forJob(job)
                    .withIdentity("Qrtz_multi_datasource_Trigger")
                    .withDescription("Sample trigger")
                    .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(1))
                    .build();
        }


        /**
         * 主数据源属性文件
         *
         * @return
         */
        @Bean
        @Primary
        @ConfigurationProperties(prefix = "spring.datasource.primary")
        public DataSourceProperties primaryDataSourceProperties() {
            return new DataSourceProperties();
        }

        /**
         * quartz调度数据源属性文件
         *
         * @return
         */
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.scheduler")
        public DataSourceProperties quartzDataSourceProperties() {
            return new DataSourceProperties();
        }

        /**
         * 主数据源
         *
         * @param dataSourceProperties 主数据源属性文件
         * @return
         */
        @Bean
        @Primary
        public DataSource dataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties dataSourceProperties) {
            return createHikariDataSource(dataSourceProperties);
        }

        /**
         * quartz调度数据源
         *
         * @param dataSourceProperties quartz调度数据源属性文件
         * @return
         */
        @Bean(name = "quartzDataSource")
        @QuartzDataSource
        public DataSource quartzDataSource(@Qualifier("quartzDataSourceProperties") DataSourceProperties dataSourceProperties) {
            return dataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        }

        private static HikariDataSource createHikariDataSource(DataSourceProperties properties) {
            HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
            if (StringUtils.hasText(properties.getName())) {
                dataSource.setPoolName(properties.getName());
            }
            return dataSource;
        }
    }
}
