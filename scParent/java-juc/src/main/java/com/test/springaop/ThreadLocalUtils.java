package com.test.springaop;

public class ThreadLocalUtils {

    private static final ThreadLocal<AbstractEventTrackingHandler> userThreadLocal = new ThreadLocal<>();

    public static void set(AbstractEventTrackingHandler user) {
        userThreadLocal.set(user);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static AbstractEventTrackingHandler get() {
        return userThreadLocal.get();
    }
}
