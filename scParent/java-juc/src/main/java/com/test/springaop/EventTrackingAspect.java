package com.test.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class EventTrackingAspect {

    @Pointcut("@annotation(com.test.springaop.EventTracking)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        // 获取处理器，装到ThreadLocl内
        AbstractEventTrackingHandler handler = getHandlerByAnnotation(joinPoint);
        if (!ObjectUtils.isEmpty(handler)) {
            handler.setParameterMap(getParameter(joinPoint));
            ThreadLocalUtils.set(handler);
        }


    }

    @After("pointCut()")
    public void controllerLog(JoinPoint joinPoint) {
        AbstractEventTrackingHandler handler = ThreadLocalUtils.get();
        if (!ObjectUtils.isEmpty(handler)) {
            ShenCeUtils.sendData(handler);
        }
        //　TODO　参数, 待处理
    }

    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        System.out.println("结果：" + returnValue);
    }

    @AfterThrowing(value = "pointCut()", throwing = "tb")
    public void ex(Throwable tb) {
        System.out.println(tb.getMessage());
    }


    private AbstractEventTrackingHandler getHandlerByAnnotation(JoinPoint joinPoint) {
        //获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        EventTracking annotation;
        if ((annotation = method.getAnnotation(EventTracking.class)) != null) {
            return EventTrackingRegister.get(annotation.eventName());
        }
        return null;
    }

    private Map<String, Object> getParameter(JoinPoint joinPoint) {
        Map<String, Object> parameterMap = new HashMap<>();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < argNames.length; i++) {
            parameterMap.put(argNames[i], args[i]);
        }
        return parameterMap;
    }

}
