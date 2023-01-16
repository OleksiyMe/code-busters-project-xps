package com.cydeo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Pointcut("@annotation(com.cydeo.annotation.ExecutionTime)")
    public void executionTimePC(){}

    @Around("executionTimePC()")
    public Object aroundAnyExecutionTimeAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        long beforeTime=System.currentTimeMillis();

        Object result=null;

        log.info ("Execution starts: Method: {}", proceedingJoinPoint.getSignature().toShortString() );

        try {
        result=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long afterTime=System.currentTimeMillis();

        log.info("Time taken to execute: Method: {} - {} ms "
                , proceedingJoinPoint.getSignature().toShortString(), (afterTime-beforeTime));

        return result;

    }



    @Pointcut("execution(* com.cydeo.service.impl.DashboardServiceImpl.getExchangeRates()))")
    public void executionTimeAPI(){}

    @Around("executionTimeAPI()")
    public Object aroundAPIExecutionTimeAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        long beforeTime=System.currentTimeMillis();

        Object result=null;

        log.info ("Execution starts: Method: {}", proceedingJoinPoint.getSignature().toShortString() );

        try {
            result=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long afterTime=System.currentTimeMillis();

        log.info("Time taken to execute: Method: {} - {} ms "
                , proceedingJoinPoint.getSignature().toShortString(), (afterTime-beforeTime));

        return result;

    }



}
