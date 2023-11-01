package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect4 {
    @Pointcut("@annotation(com.itheima.aop.MyLog)")
    private void pt(){}

    @Before("pt()")
    public void before(){
        log.info("before6....................");
    }
}
