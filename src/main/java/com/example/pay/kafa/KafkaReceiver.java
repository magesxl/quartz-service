package com.example.pay.kafa;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver {
    private static final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    /**
     * 客户端 consumer 接收消息特别简单，直接用 @KafkaListener 注解即可，并在监听中设置监听的 topic ，
     * topics 是一个数组所以是可以绑定多个主题的，上面的代码中修改为 @KafkaListener(topics = {"zhisheng","tian"}) 就可以同时监听两个 topic 的消息了。
     * 需要注意的是：这里的 topic 需要和消息发送类 KafkaSender.java 中设置的 topic 一致。
     * @param record
     */
    @KafkaListener(topics = {"test007"})
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> kafaMsg = Optional.ofNullable(record.value());
        if(kafaMsg.isPresent()){
            Object msg = kafaMsg.get();
            logger.info("----------------- record =" + record);
            logger.info("------------------ message =" + msg);
        }
    }
}
