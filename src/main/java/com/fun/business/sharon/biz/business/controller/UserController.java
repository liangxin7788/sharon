package com.fun.business.sharon.biz.business.controller;


import com.alibaba.fastjson.JSON;
import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.service.UserService;
import com.fun.business.sharon.biz.business.vo.UserVo;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.CheckParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

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
@Api(description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public GlobalResult<?> addUser(@RequestBody User user) throws NoSuchAlgorithmException {
        CheckParamUtil.checkParamForCommit(user, new String[]{"userName", "password", "description"});
        log.info("添加用户：" + JSON.toJSONString(user) + new Date());
        // 系统不允许同名用户存在
        String userName = user.getUserName();
        User hasUser = userService.judgeArleadyHasUser(userName);
        if (null != hasUser) {
            throw new OperateException("同名用户已存在，请重新添加！");
        }

        User newUser = new User();
        String password = user.getPassword();

        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] bytes = md.digest(password.getBytes());
        String savePwd = Base64.getEncoder().encodeToString(bytes);

        newUser.setPassword(savePwd);
        newUser.setCreateAt(new Date());
        newUser.setUpdateAt(new Date());
        BeanUtils.copyProperties(user, newUser);
        return GlobalResult.newSuccess(userService.save(newUser));
    }

    @ApiOperation("获取用户")
    @GetMapping("/getUserList")
    public GlobalResult<?> getUserList(@RequestBody UserVo vo){
        return GlobalResult.newSuccess(userService.getUserList(vo));
    }

    @ApiOperation("进入管理界面的校验，true才能进入")
    @GetMapping("/judgeUser")
    public GlobalResult<?> judgeUser(@RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "passWord", required = true) String passWord){
        return GlobalResult.newSuccess(userService.judgeUser(userName, passWord));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delUser")
    public GlobalResult<?> delUser(@RequestParam(value = "userId", required = true) Integer userId){
        return GlobalResult.newSuccess(userService.removeById(userId));
    }

//    @ApiOperation("测试mq")
//    @GetMapping("/testMq")
//    public GlobalResult<?> testMq(){
//
//        RabbitMqTest rabbitMqTest = new RabbitMqTest();
//        rabbitMqTest.testSend();
//
////        User user = new User();
////        userService.save(user);
//        return GlobalResult.newSuccess();
//    }
//
//    @RabbitListener(queues = {Queues.TEST_QUEUE})
//    public void recieveOther(Message message, Channel channel) {
//        try {
//            MqMessage mqMessage = JSON.parseObject(message.getBody(), MqMessage.class);
//            HashMap<String, Object> hashMap = JSON.parseObject(mqMessage.getBody(), HashMap.class);
//
//            String userName = hashMap.get("userName").toString();
//            String address = hashMap.get("address").toString();
//            String email = hashMap.get("email").toString();
//            String phone = hashMap.get("phone").toString();
//            Integer age = Integer.valueOf(hashMap.get("age").toString());
//
//            System.out.println("userName : " + userName + ", address: " + address + ", email: " + email + ", phone: " + phone + ", age: " + age);
//
//            // 给 MQ 确认消息消费，删除队列中的消息
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }

}
