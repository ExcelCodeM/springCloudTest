package com.test.simple;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class RabbitMQConcumer1 {

    public static final String QUEUE_NAME = "Hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("47.115.158.112");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        Boolean autoAck = false;
        channel.basicQos(64);

        channel.basicConsume(QUEUE_NAME, autoAck, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("consumerTag:" + consumerTag + ". Thread: " + Thread.currentThread().getId() + ". message: " + new String(body));
                channel.basicAck(deliveryTag, false);

            }
        });


//        channel.close();
//        connection.close();

    }
}
