package com.test.service.impl;

import com.test.service.IMassageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/14 22:55
 * @description：
 */

@EnableBinding(Source.class)
public class MassageProviderImpl implements IMassageProvider {

    @Autowired
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {

        String uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("*******uuid:---- "+ uuid);
        return uuid;
    }
}
