package com.test.springaop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 埋点 注解，只可以用于方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface EventTracking {

    /**
     * 事件名称
     *
     * @return
     */
    String eventName();

}
