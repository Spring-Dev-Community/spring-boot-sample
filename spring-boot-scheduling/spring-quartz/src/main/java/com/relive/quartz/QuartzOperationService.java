package com.relive.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.stereotype.Component;

/**
 * @author: ReLive
 * @date: 2022/6/15 9:58 上午
 */
@Component
@RequiredArgsConstructor
public class QuartzOperationService {

    private final Scheduler scheduler;

    public void addJob(){

    }
}
