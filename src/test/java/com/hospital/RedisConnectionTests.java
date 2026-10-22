package com.hospital;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RedisConnectionTests {
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisConnectionTests(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    String key = "learning:redis:test";

    @Test
    public void test(){
        try {
            stringRedisTemplate.opsForValue().set(key,"hello-redis");

            String actualValue = stringRedisTemplate.opsForValue().get(key);

            assertEquals("hello-redis",actualValue);
            // 写入、读取、断言
        }
        finally {
            stringRedisTemplate.delete(key);
        }

    }

}
