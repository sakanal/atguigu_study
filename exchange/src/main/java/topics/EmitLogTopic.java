package topics;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

public class EmitLogTopic {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        channel.confirmSelect();
        ConcurrentSkipListMap<Long, String> skipListMap = new ConcurrentSkipListMap<>();
        channel.addConfirmListener(
                ((deliveryTag, multiple) -> {
                    if (multiple){
                        ConcurrentNavigableMap<Long, String> headMap = skipListMap.headMap(deliveryTag);
                        headMap.clear();
                    }else {
                        skipListMap.remove(deliveryTag);
                    }
                }),
                ((deliveryTag, multiple) -> {
                    String nAckMessage = skipListMap.get(deliveryTag);
                    System.out.println("未被确认的消息："+nAckMessage+"，消息Tag："+deliveryTag);
                })
        );

        HashMap<String, String> map = new HashMap<>();
        map.put("quick.orange.rabbit","被队列Q1Q2接收到");
        map.put("quick.orange.fox","被队列Q1接收到");
        map.put("lazy.brown.fox","被队列Q2接收到 ");
        map.put("lazy.pink.rabbit","虽然满足队列Q2的两个绑定但是只会被接收一次");
        map.put("quick.orange.male.rabbit","四个单词不匹配任何绑定会被丢弃");
        
        for (Map.Entry<String, String> bindingKeyEntry : map.entrySet()) {
            String routingKey = bindingKeyEntry.getKey();
            String message = bindingKeyEntry.getValue();

            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes(StandardCharsets.UTF_8));
            skipListMap.put(channel.getNextPublishSeqNo(),message);

            System.out.println("生产者发送消息："+ message );

        }
    }
}
