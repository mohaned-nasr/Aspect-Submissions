package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object getCachedData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void cacheData(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES);  // Cache for 10 minutes
    }
}
