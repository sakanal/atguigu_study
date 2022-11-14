import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    // 队列名称
    public static final String QUEUE_NAME = "hello";

    // 接受消息
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("192.168.38.129");
        connectionFactory.setUsername("sakanal");
        connectionFactory.setPassword("RabbitMQqaz360782");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        // 声明 接受消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };

        // 声明 取消消息
        CancelCallback cancelCallback =(consumer) -> {
            System.out.println("消息消费被中断");
        };

        /*
         * 消费者接收消息
         * 参数1：表示消费哪个UI列
         * 参数2：消费成功之后，是否需要自动应答，true表示自动应答，false表示手动应答
         * 参数3：消费者成功消费的回调
         * 参数4：消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

    }
}
