package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect3 {
    @Around("com.itheima.aop.MyAspect1.pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around3..........before,,,,,");

        //调用目标对象的原石方法执行
        Object proceed = joinPoint.proceed();



        log.info("around3..........after........");

        return proceed;
    }
}
