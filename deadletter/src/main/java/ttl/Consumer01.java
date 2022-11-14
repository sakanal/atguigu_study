package ttl;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer01 {
    //普通交换机名称
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机名称
    public static final String DEAD_EXCHANGE = "dead_exchange";
    //普通队列名称
    public static final String NORMAL_QUEUE = "normal_queue";
    //死信队列名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        //声明死信和普通的交换机类型为direct
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        Map<String,Object> arguments=new HashMap<>();
        //过期时间
        //arguments.put("x-message-ttl",1000);
        //正常队列设置死信队列
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","dead");

        //声明死信和普通队列
        channel.queueDeclare(NORMAL_QUEUE,false, false,false,arguments);
        channel.queueDeclare(DEAD_QUEUE,false, false,false,null);

        //绑定普通的交换机与普通的队列
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"normal");
        //绑定死信的交换机与死信的队列
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"dead");

        System.out.println("等待接收消息......");
        channel.basicConsume(NORMAL_QUEUE,true,
                ((consumerTag, message) -> {
                    System.out.println("normalLetter接收到的消息为："+new String(message.getBody()));
                }),
                (consumerTag -> System.out.println("消息接收失败"))
        );

    }

}
