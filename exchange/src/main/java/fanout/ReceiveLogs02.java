package fanout;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;


public class ReceiveLogs02 {
    public static final String EXCHANGE_NAME="logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //声明一个队列,名称随机，当消费者断开与队列的连接时，队列自动删除
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机与队列
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        channel.basicConsume(queueName,false,
                ((consumerTag, message) -> {
                    System.out.println("ReceiveLogs02控制台打印接受到的消息：" + new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                }),
                (consumerTag -> System.out.println("消息接收失败"))
        );

    }
}
