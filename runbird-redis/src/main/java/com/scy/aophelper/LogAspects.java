package com.scy.aophelper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {

    //抽取出一个共通 的切入点
    @Pointcut("execution(public int com.scy.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    //@Before(public int com.scy.aophelper.MathCalculator.div(int int))
    public void LogBefore(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        System.out.println("---> @Before ，参数为{ " + Arrays.asList(params) + " }");
    }

    @After(value = "pointCut()")
    public void LogAfter(JoinPoint joinPoint) {
        System.out.println("---> @After 方法{ " + joinPoint.getSignature().getName() + " }运行成功");
    }

    // ！！ JoinPoint 需要位于第一个参数
    @AfterReturning(value = "pointCut()", returning = "result")
    public void LogAfterReturn(JoinPoint joinPoint, Object result) {
        System.out.println("---> @AfterReturn 方法{" + joinPoint.getSignature().getName() + "}返回结果{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void LogAfterThrowing(Exception exception) {
        System.out.println("---> @AfterThrow 方法抛出异常{" + exception + "}");
    }

}
