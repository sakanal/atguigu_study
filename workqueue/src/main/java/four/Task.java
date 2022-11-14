package four;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/*
 * 发布确认模式，
 * 1、单个确认
 * 2、批量确认
 * 3、异步批量确认
 * */
public class Task {
    public static final String QUEUE_NAME="durable_queue";
    public static final int MESSAGE_COUNT=1000;

    public static void main(String[] args) throws IOException {
        //单个确认模式
        //publishMessageIndividually();
        //批量确认模式
        //publishMessageBatch();
        //异步批量确认模式
        publishMessageAsync();
    }

    //单个确认模式
    public static void publishMessageIndividually(){
        Channel channel = RabbitMQUtils.getChannel();
        assert channel != null;

        try {
            // 开启发布确认
            channel.confirmSelect();

            long begin = System.currentTimeMillis();
            for (int i=0;i<MESSAGE_COUNT;i++){
                String message=i+"";
                channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));
                // 单个消息马上进行发布确认
                boolean flag = channel.waitForConfirms();
//                if (flag){
//                    System.out.println("消息发送成功");
//                }
            }
            long end = System.currentTimeMillis();
            System.out.println("发布"+MESSAGE_COUNT+"个单独确认消息，确认消息耗时"+(end-begin)+"毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //批量确认模式
    public static void publishMessageBatch(){

        Channel channel = RabbitMQUtils.getChannel();
        assert channel != null;

        try {
            // 开启发布确认
            channel.confirmSelect();

            long begin = System.currentTimeMillis();
            for (int i=0;i<MESSAGE_COUNT;i++){
                String message=i+"";
                channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));

                // 批量确认消息大小
                int batchSize = 100;
                // 判断达到100条消息的时候，批量确认一次
                if ((i+1)%batchSize == 0){
                    // 确认发布
                    boolean flag = channel.waitForConfirms();
//                if (flag){
//                    System.out.println("消息发送成功");
//                }
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("发布"+MESSAGE_COUNT+"个批量确认消息，确认消息耗时"+(end-begin)+"毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //异步批量确认模式
    public static void publishMessageAsync() throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        assert channel != null;
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        try {
            // 开启发布确认
            channel.confirmSelect();
            //ackCallback只会告诉你是发送失败的编号,不能给你发送失败的数据
            //所以只能把所有数据都存一边,删除发送成功的数据,这样剩下的都是发送失败的数据
            /*
             * 线程安全有序的一个哈希表 适用于高并发的情况下
             * 1、轻松地将序号与消息进行关联
             * 2、轻松地批量删除，只要给到序号
             * 3、支持高并发
             * */
            ConcurrentSkipListMap<Long, Object> outstandingConfirms  = new ConcurrentSkipListMap<>();

            //准备消息监听器，监听消息发送结果      异步通知
            /*
             * 参数1：监听哪些消息成功
             * 参数2：监听哪些消息失败
             * */
            channel.addConfirmListener(
                    //消息确认成功-回调函数
                    /*
                     * 参数1：消息的标记
                     * 参数2：是否为批量确认
                     * */
                    ((long deliveryTag,boolean multiple) -> {

                        // 删除到已经确认的消息，剩下的就是未确认的消息
                        //如果是批量处理-发送数据，则批量删除，否则单个删除
                        if(multiple){
                            ConcurrentNavigableMap<Long, Object> confirmed = outstandingConfirms.headMap(deliveryTag);
                            confirmed.clear();
                        }else {
                            outstandingConfirms.remove(deliveryTag);
                        }
                        System.out.println("确认的消息："+deliveryTag);
                    }),
                    //消息确认失败-回调函数
                    /*
                     * 参数1：消息的标记
                     * 参数2：是否为批量确认
                     * */
                    ((long deliveryTag,boolean multiple) -> {

                        // 打印一下未确认的消息都有哪些
                        Object message =  outstandingConfirms.get(deliveryTag);
                        System.out.println("未确认的消息是：" + message +"未确认的消息tag：" + deliveryTag);
                    })
            );

            long begin = System.currentTimeMillis();
            for (int i = 0; i < MESSAGE_COUNT; i++) {
                String message=i+"";
                channel.basicPublish("",QUEUE_NAME,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

                // 此处记录下所有要发送的消息的总和
                outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
            }

            long end = System.currentTimeMillis();
            System.out.println("发布"+MESSAGE_COUNT+"个异步批量确认消息，确认消息耗时"+(end-begin)+"毫秒");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
