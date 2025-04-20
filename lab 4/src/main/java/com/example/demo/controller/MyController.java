package com.example.demo.controller;

import com.example.demo.service.CacheService;
import com.example.demo.service.LockingService;
import com.example.demo.service.RateLimitingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final RateLimitingService rateLimitingService;
    private final LockingService lockingService;
    private final CacheService cacheService;

    public MyController(RateLimitingService rateLimitingService, LockingService lockingService, CacheService cacheService) {
        this.rateLimitingService = rateLimitingService;
        this.lockingService = lockingService;
        this.cacheService = cacheService;
    }

    // Rate Limiting Endpoint
    @GetMapping("/my-endpoint")
    public ResponseEntity<String> getResponse(@RequestParam String userId) {
        if (rateLimitingService.isRateLimited(userId)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Rate limit exceeded. Try again later.");
        }
        return ResponseEntity.ok("Request successful!");
    }

    // Locking Endpoint
    @GetMapping("/lock-resource")
    public ResponseEntity<String> lockResource() {
        String lockKey = "resource_lock";
        if (lockingService.acquireLock(lockKey)) {
            try {
                // Simulate resource processing
                Thread.sleep(5000); // Simulate long task
                return ResponseEntity.ok("Resource processed.");
            } catch (InterruptedException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred.");
            } finally {
                lockingService.releaseLock(lockKey);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Resource is locked.");
    }

    // Caching Endpoint
    @GetMapping("/get-data")
    public ResponseEntity<String> getData(@RequestParam String key) {
        Object cachedData = cacheService.getCachedData(key);
        if (cachedData != null) {
            return ResponseEntity.ok("Cache hit: " + cachedData);
        }
        // Simulate database call
        String dbData = "Data from DB for " + key;
        cacheService.cacheData(key, dbData);
        return ResponseEntity.ok("Cache miss. " + dbData);
    }

    // Simulate Timeout Endpoint (optional)
    @GetMapping("/simulate-timeout")
    public ResponseEntity<String> simulateTimeout() throws InterruptedException {
        // Simulate timeout by sleeping for 10 seconds
        Thread.sleep(10000);
        return ResponseEntity.ok("Request completed after timeout.");
    }
}
