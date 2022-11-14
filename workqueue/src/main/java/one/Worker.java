package one;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Worker {
    public static final String QUEUE_NAME = "hello";
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();

        DeliverCallback deliverCallback=(consumerTag , message)-> System.out.println(new String(message.getBody()));
        CancelCallback cancelCallback=(consumerTag)-> System.out.println("消息消费被中断-->"+consumerTag);

        System.out.println("worker.02");
        assert channel != null;
        channel.basicConsume(QUEUE_NAME,true, deliverCallback, cancelCallback);
    }
}
