package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/api/hello")
    @PreAuthorize("isAuthenticated()")
    public String hello() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/api/secret")
    @PreAuthorize("isAuthenticated()")
    public String secret() {
        return "Top-secret data 🙈";
    }
}

