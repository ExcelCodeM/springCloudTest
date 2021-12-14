package com.test.springaop;

import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * 埋点事件处理抽象类
 */
public abstract class AbstractEventTrackingHandler implements InitializingBean {

    /**
     * 切点 入参 map
     */
    private Map<String, Object> parameterMap;

    /**
     * 切点 结果
     */
    private Object returnValue;

    /**
     * 切点成功标志
     */
    private Boolean isSuccess;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 具体拉取事件数据的方法，每一种事件所需的数据都不同
     */
    public abstract void getData();

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
