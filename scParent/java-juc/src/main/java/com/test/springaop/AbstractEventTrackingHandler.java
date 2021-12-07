package com.test.springaop;

/**
 * 埋点事件处理抽象类
 */
public abstract class AbstractEventTrackingHandler {

    /**
     * 具体拉取事件数据的方法，每一种事件所需的数据都不同
     */
    public abstract void getData();

}
