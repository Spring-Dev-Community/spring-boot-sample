package com.relive.embeddedredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author: ReLive
 * @date: 2022/12/21 19:44
 */
@ActiveProfiles("embeddedredis")
@SpringBootTest(classes = TestRedisConfiguration.class)
public class RedisTemplateTest {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void embeddedStorageTest() {
        redisTemplate.opsForValue().set("embedded", "hello word");
        String value = (String) redisTemplate.opsForValue().get("embedded");
        assertEquals("hello word", value);
    }
}
