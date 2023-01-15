package com.cydeo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("within(com.cydeo..*)")

       public void anyPC() {}


    @AfterThrowing(pointcut="anyPC()", throwing="exception")
    public void afterThrowingAnyPC(JoinPoint joinPoint, RuntimeException exception){
        log.error("After Throwing-> Method: {}, Exception Class: {}, Exception Message: {}"
        ,joinPoint.getSignature().toShortString()
        ,exception.getClass()
        ,exception.getMessage());


    }


}
