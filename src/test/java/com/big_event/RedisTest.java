package com.big_event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        //往redis中存储一个键值对，StringRedisTemplate
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

//        operations.set("username","张三");
        operations.set("id","123456",15, TimeUnit.SECONDS);

    }

}
