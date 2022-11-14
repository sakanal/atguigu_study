package com.sakanal.rabbitmq.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class RabbitMQCallBack implements RabbitTemplate.ConfirmCallback ,RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    //@PostConstruct注解,
    // 在对象加载完依赖注入后执行它通常都是一些初始化的操作，
    // 但初始化可能依赖于注入的其他组件，所以要等依赖全部加载完再执行
    //@PostConstruct注解，在bean创建和属性赋值完成后进行初始化方法
    @PostConstruct
    public void init(){
        //注入
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /*
     * 交换机确认回调方法,发消息后，交换机接收到了就回调
     *   1.1 correlationData：保存回调消息的ID及相关信息
     *   1.2 ack:交换机收到消息，为true
     *   1.3 cause:失败原因，成功为null
     *
     * 发消息，交换机接受失败，也回调
     *   2.1 correlationData：保存回调消息的ID及相关信息
     *   2.2 ack:交换机没收到消息，为false
     *   2.3 cause:失败的原因
     *
     * */
    //解决生产者无法发布消息到交换机中--发布确认
    //生产者发布消息给交换机后需要了解到交换机是否收到了消息
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData!=null ? correlationData.getId():"";
        if (ack){
            log.info("交换机已经收到ID为：{}的信息",id);
        }else {
            log.info("交换机还未收到ID为：{}的消息，由于原因：{}",id,cause);
        }
    }

    //交换机可能无法发送消息（队列不存在，routingKey为空）给消费者，这样会导致消息被丢弃，需要告诉生产者消息被丢弃了
    //可以在当消息传递过程中不可达目的的时将消息返回给生产者
    //只有不可达目的地的时候才可回退消息
    //备份交换机的优先级比回退消息要高
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("交换机--"+returned.getExchange());
        log.error("消息--"+returned.getMessage().toString());
        log.error("消息--"+ Arrays.toString(returned.getMessage().getBody()));
        log.error("replyCode--"+returned.getReplyCode());
        log.error("replyText--"+returned.getReplyText());
        log.error("RoutingKey--"+returned.getRoutingKey());
    }
}
