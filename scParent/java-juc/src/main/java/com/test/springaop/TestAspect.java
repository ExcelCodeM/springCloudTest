package com.test.springaop;

import org.springframework.stereotype.Component;

@Component
public class TestAspect {

    @EventTracking(eventName = "sdasd")
    public void aaa(String aa) {
        System.out.println("hehe: " + Thread.currentThread().getId());
        System.out.println(1 / 0);

    }

}
