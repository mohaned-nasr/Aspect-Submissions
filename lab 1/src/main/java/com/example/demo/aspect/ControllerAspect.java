package com.example.demo.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    @Before("execution(* com.example.demo.Controllers.*.*(..))")
    public void beforeControllerMethod() {
        System.out.println("Aspect Advice: Before Controller Method Execution");
    }
}
