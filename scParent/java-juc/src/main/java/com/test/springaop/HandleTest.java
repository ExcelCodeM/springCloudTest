package com.test.springaop;

import org.springframework.stereotype.Component;

@Component
public class HandleTest extends AbstractEventTrackingHandler {

    @Override
    public void getData() {

    }

    @Override
    public void afterPropertiesSet() {
        EventTrackingRegister.register(EventTrackingRegister.EventName.REGISTER_BUTTON_CLICK.getFieldName(), this);
        System.out.println("123");
    }
}
