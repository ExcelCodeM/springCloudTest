package com.test.springaop;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class HandleTest extends AbstractEventTrackingHandler implements InitializingBean {

    @Override
    public void getData() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        EventTrackingRegister.register(EventTrackingRegister.EventName.REGISTER_BUTTON_CLICK.getFieldName(), this);
        System.out.println("123");
    }
}
