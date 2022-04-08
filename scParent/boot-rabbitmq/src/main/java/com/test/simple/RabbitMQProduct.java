package com.test.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQProduct {

    public static final String QUEUE_NAME = "Hello";
    public static final String EXCHANGE_NAME = "exchange";
    public static final String DEFAULT_SEND_MESSAGE = "Hello, this is one message";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("47.115.158.112");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        /**
         * exchange: 交换机名称
         * type： 交换机类型
         * durable： 是否持久化
         * autoDelete： 是否自动删除。自动删除的前提是至少有个队列或者交换器与这个交换器绑定 之后所有与这个交换器绑定的队列或者交换器都与解绑
         * internal： 是否内置交换机，内置交换机，不可被client直接访问，只能通过交换机路由交换机的形式访问
         * arguments: 其他结构参数
         */
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, true, false, null);

        /**
         * queue：队列名称
         * durable：是否持久化
         * exclusive：是否排他。如果一个队列被声明为排他队列，该队列仅对首次声明它的连接可见，并在连接断开时自动删除
         *              排他队列是针对Connection可见的，同一个Connection不同channel，可以同时访问同一个Connection创建的排他队列
         *              注意：即使该队列是持久化的，一旦连接关闭或者客户端退出，该排他队列都会被自动删除
         * autoDelete：是否自动删除
         *
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "hello");

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(deliveryTag + "成功，" + multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(deliveryTag + "失败，" + multiple);
            }
        });
        for (int i = 0; i < 50000; i++) {
            channel.basicPublish(EXCHANGE_NAME, "hello", null, DEFAULT_SEND_MESSAGE.getBytes());
            System.out.println("第" + i + ", 已经发送数据。。。");
        }

        Thread.sleep(5000);

        channel.close();
        connection.close();

    }


}
