package direct;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class DirectLogs {
    public static final String EXCHANGE_NAME="direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            int i = new Random().nextInt(3)+1;
            switch (i) {
                case 1:{
                    channel.basicPublish(EXCHANGE_NAME,"info",null,message.getBytes(StandardCharsets.UTF_8));
                    System.out.println("info-生产者发送消息："+message);
                    break;
                }
                case 2:{
                    channel.basicPublish(EXCHANGE_NAME,"warning",null,message.getBytes(StandardCharsets.UTF_8));
                    System.out.println("warning-生产者发送消息："+message);
                    break;
                }
                case 3:{
                    channel.basicPublish(EXCHANGE_NAME,"error",null,message.getBytes(StandardCharsets.UTF_8));
                    System.out.println("error-生产者发送消息："+message);
                    break;
                }
            }
        }


    }
}
