package com.fun.business.sharon.biz.business.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.service.UserService;
import com.fun.business.sharon.biz.mq.Queues;
import com.fun.business.sharon.biz.mq.RabbitMqTest;
import com.fun.business.sharon.biz.mq.model.MqMessage;
import com.fun.business.sharon.common.GlobalResult;
import com.rabbitmq.client.Channel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("测试mq")
    @GetMapping("/testMq")
    public GlobalResult<?> testMq(){

        RabbitMqTest rabbitMqTest = new RabbitMqTest();
        rabbitMqTest.testSend();

//        User user = new User();
//        userService.save(user);
        return GlobalResult.newSuccess();
    }

    @RabbitListener(queues = {Queues.TEST_QUEUE})
    public void recieveOther(Message message, Channel channel) {
        try {
            MqMessage mqMessage = JSON.parseObject(message.getBody(), MqMessage.class);
            HashMap<String, Object> hashMap = JSON.parseObject(mqMessage.getBody(), HashMap.class);

            String userName = hashMap.get("userName").toString();
            String address = hashMap.get("address").toString();
            String email = hashMap.get("email").toString();
            String phone = hashMap.get("phone").toString();
            Integer age = Integer.valueOf(hashMap.get("age").toString());

            System.out.println("userName : " + userName + ", address: " + address + ", email: " + email + ", phone: " + phone + ", age: " + age);

            // 给 MQ 确认消息消费，删除队列中的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
