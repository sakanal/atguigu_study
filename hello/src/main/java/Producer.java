import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static final String QUEUE_NAME="hello";

    //发消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //工厂IP 连接RabbitMQ的队列
        connectionFactory.setHost("192.168.38.129");
        //用户名
        connectionFactory.setUsername("sakanal");
        //密码
        connectionFactory.setPassword("RabbitMQqaz360782");
        //端口，默认为5672
        connectionFactory.setPort(5672);



        //创建连接
        Connection connection = connectionFactory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();

        /*
         * 创建队列
         * 参数1：队列名称
         * 参数2：队列里面的消息是否持久化，默认情况下，消息存储在内存中
         * 参数3：该队列是否只供一个消费者进行消费，是否进行消费共享，true可以多个消费者消费，false只能一个消费者消费
         * 参数4：是否自动删除：最后一个消费者断开连接之后，该队列是否自动删除，true则自动删除，false不自动删除
         * 参数5：其他参数
         * */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 发消息
        String message = "hello world";

        /*
         * 发送一个消息
         * 参数1：发送到哪个交换机
         * 参数2：路由的key值是那个，本次是队列的名称
         * 参数3：其他参数信息
         * 参数4：发送消息的消息体
         * */
//        channel.basicPublish("",QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
//        for (int i=1;i<=10;i++){
//            channel.basicPublish("",QUEUE_NAME,null,String.valueOf(i).getBytes());
//        }
        System.out.println("消息发送完毕！");
    }
}
