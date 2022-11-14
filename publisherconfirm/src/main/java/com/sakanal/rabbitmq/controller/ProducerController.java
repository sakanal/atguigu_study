package com.sakanal.rabbitmq.controller;

import com.sakanal.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/confirm")
public class ProducerController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    //发消息
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable String message){
        CorrelationData correlationData1 = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CONFIRM_EXCHANGE_NAME
                ,RabbitMQConfig.CONFIRM_routing_key
                ,message+":key"
                ,correlationData1
        );
        CorrelationData correlationData2 = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CONFIRM_EXCHANGE_NAME
                ,RabbitMQConfig.CONFIRM_routing_key+"5"
                ,message+"key5"
                ,correlationData2
        );

        log.info("发送消息内容：{}",message);
    }
}
