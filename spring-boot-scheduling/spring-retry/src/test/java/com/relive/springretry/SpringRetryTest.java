package com.relive.springretry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author: ReLive
 * @date: 2022/6/1 6:26 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = SpringRetryConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class SpringRetryTest {

    @Autowired
    private RetryTemplate retryTemplate;
    @Autowired
    private MyService myService;

    @Test(expected = RuntimeException.class)
    public void retryServiceTest() {
        myService.retryService("arg");
    }

    @Test
    public void retryServiceWithRecoverTest() {
        myService.retryServiceWithRecover("arg");
    }

    @Test(expected = RuntimeException.class)
    public void retryServiceWithCustomizationTest(){
        myService.retryServiceWithCustomization("arg");
    }

    @Test(expected = RuntimeException.class)
    public void retryServiceWithExternalizedConfiguration(){
        myService.retryServiceWithExternalizedConfiguration("arg");
    }

    @Test(expected = RuntimeException.class)
    public void retryTemplateTest() {
        retryTemplate.execute(args -> {
            myService.retryTemplate();
            return null;
        });
    }
}
