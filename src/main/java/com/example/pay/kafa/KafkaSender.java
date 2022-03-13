//package com.example.pay.kafa;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.UUID;
//
//@Component
//public class KafkaSender {
//
//    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    //发送消息方法
//    public void send(){
//        Message message = new Message();
//        message.setId(System.currentTimeMillis());
//        message.setMessage(UUID.randomUUID().toString());
//        message.setSendTime(new Date());
//        logger.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
//        //这个 topic 在 Java 程序中是不需要提前在 Kafka 中设置的，因为它会在发送的时候自动创建你设置的 topic
//        kafkaTemplate.send("test007",JSON.toJSONString(message));
//    }
//}
