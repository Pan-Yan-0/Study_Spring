package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect1 {
    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public void pt(){}
    @Before("pt()")
    public void before(){
        log.info("before.........");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around1..........before,,,,,");

        //调用目标对象的原石方法执行
        Object proceed = joinPoint.proceed();



        log.info("around1..........after........");

        return proceed;
    }

    @After("pt()")
    public void after(){
        log.info("after...........");
    }

    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("afterReturning...............");
    }

    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("afterThrowing.................");
    }
}
