package com.test.springaop;

import org.springframework.stereotype.Component;

@Component
public class TestAspect {

    @EventTracking(eventName = "RegisterButtonClick")
    public String aaa(String aa) {
        System.out.println("hehe: " + Thread.currentThread().getId());
//        System.out.println(1 / 0);

        return "123";

    }

}
