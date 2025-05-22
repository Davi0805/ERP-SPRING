package com.gnose.mvp.Logging;

import com.gnose.mvp.Exceptions.BadRequestException;
import com.gnose.mvp.Exceptions.UnauthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Component
public class LogContextAspect {

    public LogContextAspect() {}

    @Pointcut("within(@com.gnose.mvp.Logging.LogContext *)")
    public void restControllerClassMethods() {}

    @Before("restControllerClassMethods()")
    public void logContextBefore(JoinPoint joinPoint) {
        try {
            Class<?> targetClass = joinPoint.getTarget().getClass();
            LogContext logContext = targetClass.getAnnotation(LogContext.class);
            // Get of variables
            String module = logContext.module();

            // log context
            MDC.put("App-module", module);

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @After("restControllerClassMethods()")
    public void clearMDC() {
        MDC.clear();
    }
}
