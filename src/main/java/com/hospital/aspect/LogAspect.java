package com.hospital.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LogAspect {


    @Around(
            "execution(* com.hospital.service..*(..))"
    )
    public Object logAround(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {


        long start =
                System.currentTimeMillis();


        log.info(
                "接口开始：{}",
                joinPoint.getSignature()
        );


        Object result =
                joinPoint.proceed();


        long end =
                System.currentTimeMillis();


        log.info(
                "接口结束，耗时{}ms",
                end-start
        );


        return result;

    }

}