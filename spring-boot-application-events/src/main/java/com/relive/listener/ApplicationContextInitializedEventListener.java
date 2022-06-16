package com.relive.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author: ReLive
 * @date: 2022/5/19 5:14 下午
 */
@Slf4j
public class ApplicationContextInitializedEventListener implements ApplicationListener<ApplicationContextInitializedEvent> {
    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        log.info("ApplicationContextInitializedEvent 当ApplicationContext准备好并调用 ApplicationContextInitializers, 但在加载任何 bean 定义之前发送");
    }
}
