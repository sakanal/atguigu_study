package two;

import com.rabbitmq.client.Channel;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task {
    // 队列名称
    public static final String QUEUE_NAME = "ack_queue";

    // 发送大量消息
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();

        // 队列的声明
        assert channel != null;
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 从控制台中输入消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送消息完成："+ message);
        }
    }
}
