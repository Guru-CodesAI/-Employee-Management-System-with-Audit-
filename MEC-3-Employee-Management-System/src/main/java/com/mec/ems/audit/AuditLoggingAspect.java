package com.mec.ems.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** Cross-cutting audit logger kept separate from employee business logic. */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AuditLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(AuditLoggingAspect.class);

    @Around("execution(public * com.mec.ems.service.EmployeeService.*(..))")
    public Object auditEmployeeOperations(ProceedingJoinPoint joinPoint) throws Throwable {
        long startedAt = System.nanoTime();
        String operation = joinPoint.getSignature().getName();
        try {
            Object result = joinPoint.proceed();
            log.info("AUDIT outcome=SUCCESS operation={} durationMs={}",
                    operation, elapsedMillis(startedAt));
            return result;
        } catch (Throwable ex) {
            log.warn("AUDIT outcome=FAILURE operation={} exception={} durationMs={}",
                    operation, ex.getClass().getSimpleName(), elapsedMillis(startedAt));
            throw ex;
        }
    }

    private long elapsedMillis(long startedAt) {
        return (System.nanoTime() - startedAt) / 1_000_000;
    }
}
