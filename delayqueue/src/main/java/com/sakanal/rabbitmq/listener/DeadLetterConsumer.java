package com.sakanal.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class DeadLetterConsumer {
    @RabbitListener(queues = "QD")
    public void receiveD(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{}，收到的死信队列的消息是：{}", new Date(),msg);
    }

    @RabbitListener(queues = "QA")
    public void receiveA(Message message,Channel channel){
        String msg = new String(message.getBody());
        log.info("Queue_A接收到消息（破坏延迟队列）:{}",msg);
    }
}
