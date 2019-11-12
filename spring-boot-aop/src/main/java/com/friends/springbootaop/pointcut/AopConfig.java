package com.friends.springbootaop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {
    //================================execution
    @Pointcut("execution(* com.friends.springbootaop.pointcut.AspectController.aspectTest())")
    public void pointcut(){}

    @Before("pointcut()")
    public void aspectTestAop() {
        System.out.println("do something");
    }
    //================================within
    @Pointcut("within(com.friends.springbootaop.pointcut.whithin.*)")
    public void pointcutWithin(){}

    @Before("pointcutWithin()")
    public void aspectWithinAop() {
        System.out.println("hi within do something");
    }

    //================================target
    @Pointcut("target(com.friends.springbootaop.pointcut.target.TargetInterface)")
    public void pointcutTarget(){}

    @Before("pointcutTarget()")
    public void aspectTargetAop() {
        System.out.println("hi target do something");
    }

    //================================@annotation

    @Pointcut( "@annotation(org.springframework.validation.annotation.Validated)")
    public void pointcutAnnotation(){}

    @Before("pointcutAnnotation()")
    public void aspectAnnotationAop() {
        System.out.println("hi annotation do something");
    }




}
