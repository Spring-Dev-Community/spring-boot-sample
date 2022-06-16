package com.relive.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: ReLive
 * @date: 2022/5/19 6:34 下午
 */
@Slf4j
@Component
public class AvailabilityChangeEventListener implements ApplicationListener<AvailabilityChangeEvent> {
    @Override
    public void onApplicationEvent(AvailabilityChangeEvent event) {
        if (event.getState().equals(LivenessState.CORRECT)) {
            log.info("AvailabilityChangeEvent 在 LivenessState.CORRECT之后立即发送一个，表明应用程序被认为是活动的");
        } else if (event.getState().equals(ReadinessState.ACCEPTING_TRAFFIC)) {
            log.info("AvailabilityChangeEvent 在 ReadinessState.ACCEPTING_TRAFFIC之后立即发送一个，表示应用程序已准备好为请求提供服务");
        }
    }
}
