package two;

import com.rabbitmq.client.Channel;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Worker02 {
    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();

        System.out.println("Worker02等待接受消息处理时间较长");

        assert channel != null;
        channel.basicConsume(QUEUE_NAME, false,
                (consumerTag,message)->{
                    try {
                        TimeUnit.SECONDS.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("接收到的消息是："+new String(message.getBody()));
                    //进行手动应答
                    /*
                     * 参数1：消息的标记  tag
                     * 参数2：是否批量应答，false：不批量应答 true：批量
                     * */
                    channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
                },
                (consumerTag)->{
                    System.out.println(consumerTag+"消息消费被中断");
                });
    }
}
