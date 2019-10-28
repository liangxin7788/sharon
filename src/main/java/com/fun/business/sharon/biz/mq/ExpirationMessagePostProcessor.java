//package com.fun.business.sharon.biz.mq;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessagePostProcessor;
//
///**
// * @Package: com.fun.business.sharon.biz.mq
// * @ClassName: ExpirationMessagePostProcessor
// * @Description: mq事务模板
// * @Author: liangxin
// * @CreateDate: 2019/7/2 16:20
// * @UpdateDate: 2019/7/2 16:20
// */
//public class ExpirationMessagePostProcessor implements MessagePostProcessor {
//
//    private final Long ttl; // 毫秒
//
//    public ExpirationMessagePostProcessor(Long ttl) {
//        this.ttl = ttl;
//    }
//
//    @Override
//    public Message postProcessMessage(Message message) throws AmqpException {
//        message.getMessageProperties()
//                .setExpiration(ttl.toString()); // 设置per-message的失效时间
//        return message;
//    }
//
//}
