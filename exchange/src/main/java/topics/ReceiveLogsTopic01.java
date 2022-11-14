package topics;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogsTopic01 {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        channel.queueDeclare("console",false,false,false,null);
        channel.queueBind("console",EXCHANGE_NAME,"*.orange.*");
        System.out.println("等待接收消息......");

        channel.basicConsume("console",false,
                ((consumerTag, message) -> {
                    System.out.println("Topic_01-->接收到的消息为:"+new String(message.getBody()));
                    System.out.println("接收队列："+ "console" + "绑定键：" + message.getEnvelope().getRoutingKey());
                    channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                }),
                (consumerTag -> System.out.println("消息接收失败"))
        );
    }
}
