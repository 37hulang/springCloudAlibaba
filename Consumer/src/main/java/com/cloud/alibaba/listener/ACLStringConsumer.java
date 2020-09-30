package com.cloud.alibaba.listener;

import com.cloud.alibaba.entity.Consumer;
import com.cloud.alibaba.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 74716
 * @Date 2020/9/29 10:49
 **/
@Slf4j
@Service
@RocketMQMessageListener(
        topic = "rock_topic",
        consumerGroup = "spring_trans_consumer_topic_group"
)
public class ACLStringConsumer implements RocketMQListener<String> {

    @Autowired
    private ConsumerService consumerService;
    @Override
    public void onMessage(String message) {
        log.info(String.format("------- ACL StringConsumer received: %s \n", message));
        Consumer consumer = new Consumer();
        consumer.setNumber(Integer.parseInt(message));
        consumerService.insert(consumer);
    }
}
