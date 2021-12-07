package com.test.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class EventTrackingAspect {

    @Pointcut("@annotation(com.test.springaop.EventTracking)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {

        // TODO 获取处理器，装到ThreadLocl内

    }

    @After("pointCut()")//切入点描述 这个是controller包的切入点
    public void controllerLog(JoinPoint joinPoint) {

        System.out.println("haha: " + Thread.currentThread().getId());

        //获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //　TODO　参数, 待处理
        Parameter[] parameters = method.getParameters();
        EventTracking annotation;
        if ((annotation = method.getAnnotation(EventTracking.class)) != null) {
            //获取事件处理类
            AbstractEventTrackingHandler handler = EventTrackingRegister.get(annotation.eventName());
            if (!ObjectUtils.isEmpty(handler)) {
                ShenCeUtils.sendData(handler);
            }
        }
    }

    @AfterThrowing(value = "pointCut()", throwing = "tb")
    public void ex(Throwable tb) {
        System.out.println(tb.getMessage());
    }


}
