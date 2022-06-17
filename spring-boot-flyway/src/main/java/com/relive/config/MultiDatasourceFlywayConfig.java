package com.relive.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源flyway配置
 *
 * @author: ReLive
 * @date: 2022/6/17 12:41 下午
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "multi")
public class MultiDatasourceFlywayConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.seconds")
    public DataSourceProperties secondsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean("primaryDatasource")
    public DataSource primaryDatasource(@Qualifier("primaryDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    }

    @Bean("secondsDatasource")
    public DataSource secondsDatasource(@Qualifier("secondsDataSourceProperties") DataSourceProperties properties) {
        DruidDataSource dataSource = properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        this.migrate(dataSource);
        return dataSource;
    }

    private void migrate(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .baselineDescription("<<Flyway Baseline>>")
                .validateOnMigrate(true)
                .locations("/db/seconds")
                .encoding("UTF-8")
                .load();
        flyway.migrate();
    }
}
