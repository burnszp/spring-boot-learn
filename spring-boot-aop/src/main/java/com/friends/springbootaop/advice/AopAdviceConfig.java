package com.friends.springbootaop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAdviceConfig {
    //================================Before
    @Before(value = "execution(* com.friends.springbootaop.advice.AspectAdviceController.aspectBeforeExample(..))")
    public void aspectBeforeAop() {
        System.out.println("Before  do something");
    }
    //================================After
    @After("execution(* com.friends.springbootaop.advice.AspectAdviceController.aspectBeforeExample(..))")
    public void aspectAfterAop() {
        System.out.println("After  do something");
    }
    //================================AfterReturning
    @AfterReturning(
            value="execution(* com.friends.springbootaop.advice.*.*(..))",
            returning="retVal")
    public void aspectAfterReturningAop(Object retVal) {
        System.out.println("AfterReturning "+retVal.toString());
    }

    //================================AfterThrowing
    @AfterThrowing(
            pointcut="execution(* com.friends.springbootaop.advice.*.*(..))",
            throwing="ex")
    public void aspectAfterThrowingAop(Exception ex) {
        System.out.println(ex.getMessage());
    }


    //==============================Around
    @Around("execution(* com.friends.springbootaop.advice.AspectAdviceController.around(..))")
    public Object aspectAroundAop(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        System.out.println("Around start do something");
        Object retVal = pjp.proceed();
        // stop stopwatch
        System.out.println("Around return do something");
        return retVal;
    }

    //===================================parameterNormal
    @Pointcut("execution(* com.friends.springbootaop.advice.AspectAdviceController.parameterNormal(..)))")
    public void parameterNormal(){}

    @Before(value = "parameterNormal()")
    public void aspectNormalParameterAop(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("args:"+args.toString());
        Object aThis = joinPoint.getThis();
        System.out.println("this:"+aThis.toString());
        Object target = joinPoint.getTarget();
        System.out.println("target:"+target.toString());
        Signature signature = joinPoint.getSignature();
        System.out.println("signature:"+signature.toString());
        String s = joinPoint.toString();
        System.out.println("toString:"+s);
    }



    //===============================================实例化请求参数
    @Pointcut("execution(* com.friends.springbootaop.advice.AspectAdviceController.instanceParameter(..)))")
    public void InstanceParameter(){}

    @Before("InstanceParameter() && args(guy,..)")
    public void aspectInstanceParameterAop(Guy guy) {
        System.out.println("第一种："+guy.toString());
    }
    // ===============================================实例化请求参数使用如下方法也可以
    @Pointcut("InstanceParameter() && args(guy,..)")
    private void InstanceParameter2(Guy guy) {}

    @Before("InstanceParameter2(guy)")
    public void validateAccount(Guy guy) {
        System.out.println("第二种："+guy.toString());
    }

    //===============================================直接获取注解
    @Pointcut("execution(* com.friends.springbootaop.advice.AspectAdviceController.annotationParameter(..)))")
    public void annotationParameter(){}

    @Before("annotationParameter() && @annotation(auditable)")
    public void aspectAnnotationParameterAop(Auditable auditable) {
        System.out.println(auditable.value());
    }
}
