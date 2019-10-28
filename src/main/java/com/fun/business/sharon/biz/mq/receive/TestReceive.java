//package com.fun.business.sharon.biz.mq.receive;
//
//import com.alibaba.fastjson.JSON;
//import com.fun.business.sharon.biz.mq.Queues;
//import com.fun.business.sharon.biz.mq.model.MqMessage;
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//
//import java.util.HashMap;
//
///**
// * @Package: com.fun.business.sharon.biz.mq.receive
// * @ClassName: TestReceive
// * @Description: MQ接收测试类
// * @Author: liangxin
// * @CreateDate: 2019/7/2 16:40
// * @UpdateDate: 2019/7/2 16:40
// */
//@Slf4j
//public class TestReceive {
//
//    @RabbitListener(queues = {Queues.TEST_QUEUE})
//    public void recieveOther(Message message, Channel channel) {
//        log.info(message.toString());
//        try {
//            MqMessage mqMessage = JSON.parseObject(message.getBody(), MqMessage.class);
//            HashMap hashMap = JSON.parseObject(mqMessage.getBody(), HashMap.class);
//            // 操作数据
//            saveData(hashMap);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    private String saveData(HashMap hashMap) {
//
//
//        return  null;
//    }
//
//}
