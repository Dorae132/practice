package com.nsb.practice.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * entrance test
 * 
 * @author Dorae
 *
 */
public class Main {
    public static final String TOPIC = "TopicTest";
    public static final String TAG = "TagA";
    
    public static void main(String[] args) throws Exception {
//        Consumer.consume();
        DefaultMQProducer producer = new DefaultMQProducer("producer_demo");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 100000; i++) {
            System.out.printf("%s%n", Producer.send(producer, TOPIC, TAG, "测试RocketMQ" + i));
        }
    }
}
