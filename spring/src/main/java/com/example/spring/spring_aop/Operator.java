package com.example.spring.spring_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author gavin
 * @date 2018/12/6 15:06
 */
@Component
@Aspect
@EnableAspectJAutoProxy
public class Operator {

    // execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)
    @Pointcut("execution(* com.example.spring.spring_aop..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("Aop before advice ...");
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("Aop after advice ...");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal) {
        System.out.println("Aop afterReturning advice:" + returnVal);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("Aop afterThrowing advice ..." + error);
    }

    /// Around环绕通知与AfterThrowing和AfterReturning冲突，不要同时开启，他的优先级比before高，是最重要的通知
//    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) {
        System.out.println("Aop around before...");
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Aop around after...");
    }
}
