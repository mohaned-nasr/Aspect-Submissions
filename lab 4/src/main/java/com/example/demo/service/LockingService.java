package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LockingService {

    private final RedisTemplate<String, Object> redisTemplate;

    public LockingService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean acquireLock(String key) {
        Boolean isLocked = redisTemplate.opsForValue().setIfAbsent(key, "locked", 10, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(isLocked);
    }

    public void releaseLock(String key) {
        redisTemplate.delete(key);
    }
}
