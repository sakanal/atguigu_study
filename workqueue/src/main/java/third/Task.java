package third;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Task {
    public static final String QUEUE_NAME = "durable_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();

        assert channel != null;
        //队列持久化，durable参数为true
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        String message = "durable test";

        //消息持久化，props参数为MessageProperties.PERSISTENT_TEXT_PLAIN
        channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));

        System.out.println("发送消息完成："+ message);
    }
}
