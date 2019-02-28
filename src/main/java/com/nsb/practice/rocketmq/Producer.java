package com.nsb.practice.rocketmq;

import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * producer
 * @author Dorae
 *
 */
public class Producer {
    public static SendResult send(MQProducer mqProducer, String topic, String tag, String msg) throws Exception {
        Message message = new Message(topic, tag, msg.getBytes());
        return mqProducer.send(message);
    }
}
