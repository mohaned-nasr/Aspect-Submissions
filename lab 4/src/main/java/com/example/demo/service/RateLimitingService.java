package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RateLimitingService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RateLimitingService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isRateLimited(String userId) {
        String key = "rate_limit:" + userId;
        Long count = redisTemplate.opsForValue().increment(key, 1);
        if (count == 1) {
            redisTemplate.expire(key, 1, TimeUnit.MINUTES);  // expire after 1 minute
        }
        return count > 5;  // Allow a maximum of 5 requests per minute
    }
}
