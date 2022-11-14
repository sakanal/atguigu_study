package topics;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogsTopic02 {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        channel.queueDeclare("disk",false,false,false,null);
        channel.queueBind("disk",EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind("disk",EXCHANGE_NAME,"lazy.#");
        System.out.println("等待接收消息......");

        channel.basicConsume("disk",false,
                ((consumerTag, message) -> {
                    System.out.println("Topic_02-->接收到的消息为:"+new String(message.getBody()));
                    System.out.println("接收队列："+ "disk" + "绑定键：" + message.getEnvelope().getRoutingKey());
                    channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                }),
                (consumerTag -> System.out.println("消息接收失败"))
        );
    }
}
