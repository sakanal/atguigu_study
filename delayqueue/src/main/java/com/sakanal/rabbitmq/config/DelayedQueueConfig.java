package com.sakanal.rabbitmq.config;

import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayedQueueConfig {
    //队列
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    //交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    //routingKey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    @Bean
    public CustomExchange delayedExchange(){
        Map<String,Object> arguments=new HashMap<>();
        arguments.put("x-delayed-type", BuiltinExchangeType.DIRECT.getType());
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true,false,arguments);
    }
    @Bean
    public Queue delayedQueue(){
        return QueueBuilder.durable(DELAYED_QUEUE_NAME).build();
    }
    @Bean
    public Binding bindingDelayedQueueWithExchange(@Qualifier("delayedExchange")CustomExchange delayedExchange,
                                                   @Qualifier("delayedQueue")Queue delayedQueue){
        return BindingBuilder
                .bind(delayedQueue)
                .to(delayedExchange)
                .with(DELAYED_ROUTING_KEY)
                .noargs();
    }
}
