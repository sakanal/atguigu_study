package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils{
    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.38.129");
        connectionFactory.setUsername("sakanal");
        connectionFactory.setPassword("RabbitMQqaz360782");
        connectionFactory.setPort(5672);
        return connectionFactory.newConnection().createChannel();
    }
}
