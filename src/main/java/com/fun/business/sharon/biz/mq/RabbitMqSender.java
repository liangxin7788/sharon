//package com.fun.business.sharon.biz.mq;
//
//import com.fun.business.sharon.biz.mq.model.MqMessage;
//import com.fun.business.sharon.common.OperateException;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * MQ 发送消息类
// */
//@Component
//public class RabbitMqSender {
//
//    /** 标准模板 */
//    @Resource
//    private RabbitTemplate rabbitTemplate;
//
//    /** 事物模板 */
//    @Resource
//    private RabbitTemplate transactRabbitTemplate;
//
//    /** confirm模板 */
//    @Resource
//    private RabbitTemplate confirmRabbitTemplate;
//
//    /** return模板 */
//    @Resource
//    private RabbitTemplate returnRabbitTemplate;
//
//    /** confirmAndReturn模板 */
//    @Resource
//    private RabbitTemplate confirmAndReturnRabbitTemplate;
//
//    /** confirmAndReturn抛异常模板 */
//    @Resource
//    private RabbitTemplate confirmAndReturnThrowExceptionRabbitTemplate;
//
//    /**
//     * 功能描述: 发送消息
//     * @param exchange	交换机
//	 * @param routingKey	队列名
//	 * @param message	消息
//     * @Author: Frank
//     * @Date: 2019/6/13
//     * @Version: 0.0.1
//     */
//    public void send(String exchange, String routingKey, Object message) {
//        judgeException(exchange, routingKey);
//        // 发送消息
//        rabbitTemplate.convertAndSend(exchange, routingKey, message);
//    }
//
//    /**
//     * 功能描述: 发送消息
//     *
//     * @param exchange  交换机名称
//     * @param routingKey  队列名
//     * @param mqMessage 队列消息
//     * @Author: Lenly
//     * @Date: 2018/12/31
//     * @Version: 0.0.1
//     **/
//    public void send(String exchange, String routingKey, MqMessage mqMessage) {
//        judgeException(exchange, routingKey);
//        // 发送消息
//        rabbitTemplate.convertAndSend(exchange, routingKey, mqMessage);
//    }
//
//    public void send(String exchange, String routingKey, MqMessage mqMessage, long expiration) {
//        judgeException(exchange, routingKey);
//        // 发送消息
//        rabbitTemplate.convertAndSend(exchange, routingKey, mqMessage, new ExpirationMessagePostProcessor(expiration));
//    }
//
//    /**
//     * 功能描述: 事物机制发送消息
//     *
//     * @param exchange  交换机名称
//     * @param routingKey  队列名
//     * @param mqMessage 队列消息
//     * @Author: Lenly
//     * @Date: 2018/12/31
//     * @Version: 0.0.1
//     **/
//    public void transactSend(String exchange, String routingKey, MqMessage mqMessage) {
//        judgeException(exchange, routingKey);
//        // 发送消息
//        transactRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage);
//    }
//
//    /**
//     * 功能描述: confirm机制发送消息
//     *
//     * @param exchange  交换机名称
//     * @param routingKey  队列名
//     * @param mqMessage 队列消息
//     * @param messageId 发送消息的id(用于消息发送失败后存在数据库的，所以最好是容易辨认的)
//     * @Author: Lenly
//     * @Date: 2018/12/31
//     * @Version: 0.0.1
//     **/
//    public void confirmSend(String exchange, String routingKey, MqMessage mqMessage, String messageId) {
//        judgeException(exchange, routingKey);
//        /** 组装交换机ID */
//        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId(exchange + ":" + routingKey + ":" + messageId);
//        // 发送消息
//        confirmRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage,correlationData);
//    }
//
//    /**
//     * 功能描述: return机制发送消息
//     *
//     * @param exchange  交换机名称
//     * @param routingKey  队列名
//     * @param mqMessage 队列消息
//     * @Author: Lenly
//     * @Date: 2018/12/31
//     * @Version: 0.0.1
//     **/
//    public void returnSend(String exchange, String routingKey, MqMessage mqMessage) {
//        judgeException(exchange, routingKey);
//        // 发送消息
//        returnRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage);
//    }
//
//    public void returnSend(String exchange, String routingKey, MqMessage mqMessage, long expiration) {
//        judgeException(exchange, routingKey);
//        // 发送消息
//        returnRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage, new ExpirationMessagePostProcessor(expiration));
//    }
//
//    /**
//     * 功能描述:  confirm和return机制发送消息
//     *
//     * @param exchange  交换机名称
//     * @param routingKey  队列名
//     * @param mqMessage 队列消息
//     * @param messageId 发送消息的id(用于消息发送失败后存在数据库的，所以最好是容易辨认的)
//     * @Author: Lenly
//     * @Date: 2018/12/31
//     * @Version: 0.0.1
//     **/
//    public void confirmAndReturnSend(String exchange, String routingKey, MqMessage mqMessage, String messageId) {
//        judgeException(exchange, routingKey);
//        /** 组装交换机ID */
//        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId(exchange + ":" + routingKey + ":" + messageId);
//        // 发送消息
//        confirmAndReturnRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage, correlationData);
//    }
//
//    public void confirmAndReturnSend(String exchange, String routingKey, MqMessage mqMessage, String messageId, long expiration) {
//        judgeException(exchange, routingKey);
//        /** 组装交换机ID */
//        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId(exchange + ":" + routingKey + ":" + messageId);
//        // 发送消息
//        confirmAndReturnRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage, new ExpirationMessagePostProcessor(expiration), correlationData);
//    }
//
//    /**
//     * 功能描述:  confirm和return机制发送消息
//     *
//     * @param exchange  交换机名称
//     * @param routingKey  队列名
//     * @param mqMessage 队列消息
//     * @param messageId 发送消息的id(用于消息发送失败后存在数据库的，所以最好是容易辨认的)
//     * @Author: Mike
//     * @Date: 2019/1/11
//     * @Version: 0.0.1
//     **/
//    public void confirmAndReturnThrowExceptionSend(String exchange, String routingKey, MqMessage mqMessage, String messageId) {
//        judgeException(exchange, routingKey);
//        //RabbitTemplate confirmAndReturnRabbitTemplate = SpringUtils.getBean("confirmAndReturnRabbitTemplate",RabbitTemplate.class);
//
//        /** 组装交换机ID */
//        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId(exchange + ":" + routingKey + ":" + messageId);
//        // 发送消息
//        confirmAndReturnThrowExceptionRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage, correlationData);
//    }
//
//    public void confirmAndReturnThrowExceptionSend(String exchange, String routingKey, MqMessage mqMessage, String messageId, long expiration) {
//        judgeException(exchange, routingKey);
//        //RabbitTemplate confirmAndReturnRabbitTemplate = SpringUtils.getBean("confirmAndReturnRabbitTemplate",RabbitTemplate.class);
//
//        /** 组装交换机ID */
//        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId(exchange + ":" + routingKey + ":" + messageId);
//        // 发送消息
//        confirmAndReturnThrowExceptionRabbitTemplate.convertAndSend(exchange, routingKey, mqMessage, new ExpirationMessagePostProcessor(expiration), correlationData);
//    }
//
//    private void judgeException(String exchange, String routingKey){
//        if(null == exchange || "".equals(exchange)){
//            throw new OperateException("交换机不能为空");
//        }
//        if(null == routingKey || "".equals(routingKey)){
//            throw new OperateException("routingKey不能为空");
//        }
//    }
//}
