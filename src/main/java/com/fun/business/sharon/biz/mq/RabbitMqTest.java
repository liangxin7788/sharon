//package com.fun.business.sharon.biz.mq;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.fun.business.sharon.biz.mq.model.MqMessage;
//import com.fun.business.sharon.biz.mq.model.MqType;
//import com.fun.business.sharon.utils.SpringUtil;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.HashMap;
//
///**
// * @Package: com.fun.business.sharon.biz.mq
// * @ClassName: RabbitMqTest
// * @Description: RabbitMq测试类
// * @Author: liangxin
// * @CreateDate: 2019/7/2 16:13
// * @UpdateDate: 2019/7/2 16:13
// */
//@Component
//public class RabbitMqTest {
//
//    @Autowired
//    private RabbitMqSender rabbitMqSender;
//
//    public void testSend() {
//
////        for (int i = 0; i < 10000; i++) {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("userName", "liangxin");
//            hashMap.put("address", "wuhe");
//            hashMap.put("email", "294051211@qq.com");
//            hashMap.put("phone", "15201174509");
//            hashMap.put("age", 18);
//
//            MqMessage mqMessage = new MqMessage(false, MqType.INFO, "测试服务名", "test", "system",
//                    "synchronize data info", JSON.toJSONString(hashMap), new String[] {"aaa", "111"});
//            RabbitMqSender rabbitMqSender = SpringUtil.getBean(RabbitMqSender.class);
//            rabbitMqSender.send("TEST_EXCHANGE_2","TEST_ROUTING_KEY",mqMessage);
////            this.rabbitMqSender.send("TEST_EXCHANGE","TEST_ROUTING_KEY",mqMessage);
////            rabbitMqSender.confirmAndReturnSend("TEST_EXCHANGE", "TEST_ROUTING_KEY", mqMessage, "XXX");
////        }
//
//    }
//
////    @RabbitListener(queues = { Queues.TEST_QUEUE })
////    public void cosumerMessage(Message message, Channel channel) {
//        // 如果消息头部有error，说明该消息存在问题
////        if (message.getMessageProperties().getHeaders().get("error") != null) {
////            try {
//                // // 如果消息 已经被消费了，但是消费失败了
//                // if (message.getMessageProperties().getRedelivered()){
//                // // 拒绝消息
//                // channel.basicReject(message.getMessageProperties().getDeliveryTag(),
//                // true);
//                // }else{
//                // //重新放入消息队列
//                // (requeue=true，表示该消息将重新放回MQ并重新排队；requeue=false，表示将删除该消息)
//                // channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
//                // }
//
//                // 重新放入消息队列
//                // (requeue=true，表示该消息将重新放回MQ并重新排队；requeue=false，表示将删除该消息)
////                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
////                return;
////            }
////            catch (IOException e) {
////                // 异常处理
////            }
////        }
//
////        try {
////            MqMessage mqMessage = JSON.parseObject(message.getBody(), MqMessage.class);
////            HashMap<String, Object> hashMap = JSON.parseObject(mqMessage.getBody(), HashMap.class);
////
////            String userName = hashMap.get("userName").toString();
////            String address = hashMap.get("address").toString();
////            String email = hashMap.get("email").toString();
////            String phone = hashMap.get("phone").toString();
////            Integer age = Integer.valueOf(hashMap.get("age").toString());
////
////            System.out.println("userName : " + userName + ", address: " + address + ", email: " + email + ", phone: " + phone + ", age: " + age);
////
////            // 给 MQ 确认消息消费，删除队列中的消息
////            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
////        }
////        catch (Exception e) {
////            // 如果确认消息异常，回滚事物
////
////            // 如果消息 已经被消费了，但是消费失败了
////            // if (message.getMessageProperties().getRedelivered()){
////            // // 拒绝消息
////            // try {
////            // channel.basicReject(message.getMessageProperties().getDeliveryTag(),
////            // true);
////            // } catch (IOException e1) {
////            // e1.printStackTrace();
////            // }
////            // }
////
////        }
////    }
//
//}
