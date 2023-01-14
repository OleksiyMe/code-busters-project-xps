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

    @Pointcut("execution(* com.cydeo.*.*(..))")
       public void anyPC() {}


    @AfterThrowing(pointcut="anyPC()", throwing="exception")
    public void afterThrowingAnyPC(JoinPoint joinPoint, Exception exception){
        log.info("After Throwing-> Method: {}, Excecption Class: {}, Message: {]"
        , joinPoint.getSignature().toShortString()
        ,getClass()
        ,exception.getMessage());


    }


}
