package four;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Worker {
    public static final String QUEUE_NAME="durable_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        assert channel != null;

        System.out.println("开始接收消息");
        channel.basicConsume(QUEUE_NAME,false,
                ((String consumerTag, Delivery message) -> {
                    System.out.println("接收到的消息为："+new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                }),
                ((String consumerTag) -> System.out.println("消息消费失败"))
        );
    }
}
