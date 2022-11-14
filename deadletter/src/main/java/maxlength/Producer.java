package maxlength;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

public class Producer {
    //普通交换机名称
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);

        channel.confirmSelect();
        ConcurrentSkipListMap<Long, String> skipListMap = new ConcurrentSkipListMap<>();
        channel.addConfirmListener(
            ((deliveryTag, multiple) -> {
                if (multiple) {
                    ConcurrentNavigableMap<Long, String> headMap = skipListMap.headMap(deliveryTag);
                    headMap.clear();
                }else {
                    skipListMap.remove(deliveryTag);
                }
            }),
            ((deliveryTag, multiple) -> {
                System.out.println("发布未确认的消息："+skipListMap.get(deliveryTag));
            })
        );

        for (int i = 0; i < 10; i++) {
            String message="info"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"normal",null,message.getBytes(StandardCharsets.UTF_8));
            skipListMap.put(channel.getNextPublishSeqNo(),message);

        }
    }
}
