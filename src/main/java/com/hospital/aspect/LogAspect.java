package com.hospital.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Around("execution(* com.hospital.controller..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            log.info("{} 调用成功，耗时 {} ms", method, System.currentTimeMillis() - start);
            return result;
        } catch (Throwable throwable) {
            log.error("{} 调用失败，耗时 {} ms", method, System.currentTimeMillis() - start, throwable);
            throw throwable;
        }
    }
}
