package com.relive.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: ReLive
 * @date: 2022/5/19 7:01 下午
 */
@Slf4j
@Component
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        log.info("ApplicationFailedEvent 启动时出现异常，则发送一个");
    }
}
