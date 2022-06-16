package com.relive.springretry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: ReLive
 * @date: 2022/6/1 6:37 下午
 */
@Slf4j
@Service
public class MyServiceImpl implements MyService {

    @Override
    public void retryService(String sql) {
        log.info("重试...MyServiceImpl retryService()");
        throw new RuntimeException();
    }

    @Override
    public void retryServiceWithRecover(String arg) {
        log.info("重试...MyServiceImpl retryServiceWithRecover");
        throw new IllegalArgumentException();
    }

    @Override
    public void recover(IllegalArgumentException e, String arg) {
        log.info("恢复...MyServiceImpl recover()");
    }

    @Override
    public void retryServiceWithCustomization(String arg) {
        log.info("重试...MyServiceImpl retryServiceWithCustomization()");
        throw new RuntimeException();
    }

    @Override
    public void retryServiceWithExternalizedConfiguration(String arg) {
        log.info("重试...MyServiceImpl retryServiceWithExternalizedConfiguration()");
        try {
            Thread.sleep(200000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public void retryTemplate() {
        log.info("重试...MyServiceImpl retryTemplate()");
        throw new RuntimeException();
    }
}
