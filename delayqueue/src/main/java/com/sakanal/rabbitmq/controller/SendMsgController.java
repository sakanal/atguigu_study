package com.sakanal.rabbitmq.controller;

import com.sakanal.rabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("当前时间：{}，发送一条信息给两个TTL队列：{}",new Date().toString(),message);

        rabbitTemplate.convertAndSend("X","XA","消息来自TTL为10s的队列：" + message);
        rabbitTemplate.convertAndSend("X","XB","消息来自TTL为30s的队列：" + message);

    }

    @GetMapping("/sendExpirationMsg/{ttlTime}/{message}")
    public void sendExpirationMsg(@PathVariable("ttlTime")String ttlTime,
                                  @PathVariable("message")String message){
        log.info("当前时间：{}，发送过期时间为：{}毫秒的TTL消息到延迟队列中：{}",new Date(),ttlTime,message);

        rabbitTemplate.convertAndSend("X","XC",message,
                (msg->{
                    msg.getMessageProperties().setExpiration(ttlTime);
                    return msg;
                })
        );
    }

    //开始发消息 基于插件的 消息 及 延迟的时间
    @GetMapping("/sendDelayMsg/{delayTime}/{message}")
    public void sendMsg(@PathVariable String message,@PathVariable Integer delayTime){
        log.info("当前时间：{}，发送一条时长{}毫秒信息给延迟队列delayed.queue：{}",
                new Date().toString(),delayTime,message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME
                ,DelayedQueueConfig.DELAYED_ROUTING_KEY,
                message,
                (msg -> {
                    // 发送消息的时候 延迟时长 单位ms
                    msg.getMessageProperties().setDelay(delayTime);
                    return msg;
                })
        );
    }

}
