package com.test.springaop;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储 事件与handler的关系
 */
public class EventTrackingRegister {

    private static final Map<String, AbstractEventTrackingHandler> HANDLE_MAP = new HashMap<>();

    public static void register(String eventName, AbstractEventTrackingHandler handler) {
        HANDLE_MAP.put(eventName, handler);
    }

    public static AbstractEventTrackingHandler get(String eventName) {
        return HANDLE_MAP.get(eventName);
    }


    /**
     * 事件名称
     */
    public enum EventName {
        // TODO
        REGISTER_BUTTON_CLICK("RegisterButtonClick", "点击注册按钮");


        private String fieldName;

        private String showName;

        EventName(String fieldName, String showName) {
            this.fieldName = fieldName;
            this.showName = showName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getShowName() {
            return showName;
        }

    }


}
