package com.sakanal.rabbitmq.listener;

import com.sakanal.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {
    @RabbitListener(queues = RabbitMQConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmMessage(Message message){
        String msg = new String(message.getBody());
        log.info("接受到的队列confirm.queue消息：{}",msg);
    }

    //备份交换机发送消息
    //优先级比回退消息(RabbitTemplate.ReturnsCallback.returnedMessage)高
    @RabbitListener(queues = RabbitMQConfig.BACKUP_QUEUE_NAME)
    public void backupMessage(Message message){
        String msg = new String(message.getBody());
        log.error("报警发现不可路由消息：{}",msg);
    }
}

