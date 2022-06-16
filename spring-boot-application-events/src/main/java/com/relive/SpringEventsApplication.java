package com.relive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: ReLive
 * @date: 2022/5/19 4:18 下午
 */
@SpringBootApplication
public class SpringEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventsApplication.class, args);
    }

//    @Bean
//    public Object object(){
//        throw new RuntimeException("启动出错");
//    }
}
