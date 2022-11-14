package third;

import com.rabbitmq.client.Channel;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Worker {
    public static final String QUEUE_NAME = "durable_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        assert channel != null;

        System.out.println("开始接收消息");

        //设置不公平分发
        //每次分配给该消费者的任务数量不超过1条，若超过一条分配给不忙的消费者
        //这个是设置信道容量的大小，采用轮询的方式往信道放消息，信道满了就跳过
        //不设置 basicQos的话是一次性平均分发给所有的队列，设置之后限制了一次分发消息的数量，
        // 再设置手动确认机制，这样在你没有提交已经处理好的消息的时候是不会给你分发消息的。实现的不公平分发。
        int prefetchCount=1;
        channel.basicQos(prefetchCount);

        channel.basicConsume(QUEUE_NAME,false,
                ((consumerTag, message) -> {
                    System.out.println("接收到的消息："+new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                }),
                (consumerTag -> System.out.println(consumerTag+"消息消费被中断")));
    }
}
