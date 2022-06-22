package com.relive.springretry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author: ReLive
 * @date: 2022/6/1 6:31 下午
 */
public interface MyService {
    /**
     * Retry默认行为，重试最多可能发生 3 次，重试之间有 1 秒的延迟
     *
     * @param arg
     */
    @Retryable(value = RuntimeException.class)
    void retryService(String arg);

    /**
     * 如果retryServiceWithRecover方法在 3 次尝试后仍然抛出 IllegalArgumentException，则会调用recover()方法
     *
     * @param arg
     */
    @Retryable(value = IllegalArgumentException.class)
    void retryServiceWithRecover(String arg);

    /**
     * 恢复方法
     *
     * @param e
     * @param arg
     */
    @Recover
    void recover(IllegalArgumentException e, String arg);

    /**
     * 自定义重试行为，最多重试2次，每次间隔100毫秒
     *
     * @param arg
     */
    @Retryable(value = RuntimeException.class,
            maxAttempts = 2, backoff = @Backoff(delay = 100))
    void retryServiceWithCustomization(String arg);

    /**
     * delay和maxAttempts的值配置到外部文件中，注意此时使用的是maxAttemptsExpression和delayExpression
     *
     * @param arg
     */
    @Retryable(value = RuntimeException.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    void retryServiceWithExternalizedConfiguration(String arg);

    /**
     * 使用RetryTemplate进行重试行为
     */
    void retryTemplate();
}
