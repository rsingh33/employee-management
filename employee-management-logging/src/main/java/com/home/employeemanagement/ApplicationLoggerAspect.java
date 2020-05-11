package com.home.employeemanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log =
            LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.home.employeemanagement.controllers..*)")
    public void controllerPointCut() {
    }

 /*   @After("controllerPointCut()")
    public void logAfter(JoinPoint joinPoint) {
        log.debug("\n \n \n");
        log.debug("######## After Advice Method Execution ######### \n {} . {} with arguments[s] {}",
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getDeclaringTypeName(),
                Arrays.toString(joinPoint.getArgs()));
        log.debug("________________________________________________________________________ \n \n");

    }*/

    @Around("controllerPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("######## Before Advice Method Execution ######### \n {} . {} with arguments[s] {}",
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getDeclaringTypeName(),
                Arrays.toString(joinPoint.getArgs()));

        log.debug("________________________________________________________________________ \n \n");

       Object o =  joinPoint.proceed();

        log.debug("######## After Advice Method Execution ######### \n {} . {} with arguments[s] {}",
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getDeclaringTypeName(),
                Arrays.toString(joinPoint.getArgs()));
        log.debug("________________________________________________________________________ \n \n");

        return o;
    }
}
