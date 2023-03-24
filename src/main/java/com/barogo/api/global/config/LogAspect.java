package com.barogo.api.global.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("execution(* com.barogo.api.domain.*..service.*Service.*(..))")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        log.info("==> LogAspect Root : " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("==> LogAspect Method : " + joinPoint.getSignature().getName());
        return result;
    }

}
