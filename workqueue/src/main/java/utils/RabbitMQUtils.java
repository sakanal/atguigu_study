package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    public static Channel getChannel(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.38.129");
        connectionFactory.setUsername("sakanal");
        connectionFactory.setPassword("RabbitMQqaz360782");
        connectionFactory.setPort(5672);
        try {
            Connection connection = connectionFactory.newConnection();
            return connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
