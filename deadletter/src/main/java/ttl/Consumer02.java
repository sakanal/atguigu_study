package ttl;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer02 {
    //死信交换机名称
    public static final String DEAD_EXCHANGE = "dead_exchange";
    //死信队列名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        //声明死信类型为direct
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        //声明死信队列
        channel.queueDeclare(DEAD_QUEUE,false, false,false,null);

        //绑定死信的交换机与死信的队列
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"dead");

        System.out.println("等待接收消息......");
        channel.basicConsume(DEAD_QUEUE,true,
                ((consumerTag, message) -> {
                    System.out.println("deadLetter接收到的消息为："+new String(message.getBody()));
                }),
                (consumerTag -> System.out.println("消息接收失败"))
        );

    }

}
