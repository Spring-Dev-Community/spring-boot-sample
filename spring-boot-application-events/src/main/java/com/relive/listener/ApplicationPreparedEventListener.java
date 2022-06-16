package com.relive.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: ReLive
 * @date: 2022/5/19 5:29 下午
 */
@Slf4j
@Component
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        log.info("ApplicationPreparedEvent 在refresh开始之前但在加载 bean 定义之后发送");
    }
}
