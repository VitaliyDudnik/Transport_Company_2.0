package com.example.transport_company.aopAspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class AopAspect {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    @Pointcut(value = "execution(* com.example.transport_company.service.*.*(..))")
    protected void servicePackage() {
    }

    @Pointcut(value = "execution(* com.example.transport_company.caching.*.*(..))")
    protected void caching() {
    }

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }

    @Before(value = "servicePackage()")
    public void beforeServiceLog(JoinPoint joinPoint) {
        log.info("=================Before service Start======================");
        log.info("Method called at: " + LocalDateTime.now().format(format));
        log.info("Entering in Method:  " + joinPoint.getSignature().getName());
        log.info("Method package:  " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Arguments:  " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before(value = "controller()")
    public void beforeControllerLog(JoinPoint joinPoint) {
        log.info("=================Before controller Start======================");
        log.info("Method called at: " + LocalDateTime.now().format(format));
        log.info("Entering in Method:  " + joinPoint.getSignature().getName());
        log.info("Method package:  " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Arguments:  " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "servicePackage() & controller()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("=================After returning start=======================");
        log.info("The method that returned the value: " + joinPoint.getSignature().getName());
        log.info("Method package: " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Returned value is empty: '{}'", result.toString().isEmpty());
    }

    @AfterThrowing(value = "servicePackage()", throwing = "ex")
    public void afterThrowingInService(JoinPoint joinPoint, Exception ex) {
        log.error("After Throwing exception in class: " + joinPoint.getTarget().getClass());
        log.error("Throwing exception in method: " + joinPoint.getSignature().getName());
        log.error("Exception message: " + ex.getMessage());
        log.error("Exception cause: " + ex.getCause());
        log.error("Throwing timestamp: " + LocalDateTime.now().format(format));
    }

    @Around(value = "servicePackage()")
    public Object aroundServicePackage(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        Object retrieval = proceedingJoinPoint.proceed();
        long end = System.nanoTime();
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("Execution of " + methodName + " took " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retrieval;
    }

    @Around(value = "caching()")
    public Object aroundCachingPackage(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        Object retrieval = proceedingJoinPoint.proceed();
        long end = System.nanoTime();
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("Execution of " + methodName + " took " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retrieval;
    }

    @AfterReturning(pointcut = "caching()", returning = "result")
    public void afterReturningCache(JoinPoint joinPoint, Object result) {
        log.info("=================After returning cache start=======================");
        log.info("The method that returned the value: " + joinPoint.getSignature().getName());
        log.info("Method package: " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Returned value: {}", result);
    }
}
