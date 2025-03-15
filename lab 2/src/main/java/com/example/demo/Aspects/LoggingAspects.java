package com.example.demo.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {

    @Before("execution(* com.example.demo.Users.UserController.*(..)) && args(user, ..)")
    public void logBeforeControllerMethods(JoinPoint joinPoint, Object user) {
        System.out.println("Method called: " + joinPoint.getSignature().getName());
        if (user != null) {
            System.out.println("Request body: " + user);
        } else {
            System.out.println("No request body found.");
        }
    }
}
