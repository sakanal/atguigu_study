package direct;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogsDirect02 {
    public static final String EXCHANGE_NAME="direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        channel.queueDeclare("disk",false,false,false,null);

        channel.queueBind("disk",EXCHANGE_NAME,"warning");
        channel.queueBind("disk",EXCHANGE_NAME,"error");

        channel.basicConsume("disk",true,
                ((consumerTag, message) -> System.out.println("ReceiveLogsDirect02-接收到的消息："+new String(message.getBody()))),
                (consumerTag -> System.out.println("接收消息失败"))
        );
    }
}
