//package com.fun.business.sharon.biz.mq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Package: com.fun.business.sharon.biz.mq
// * @ClassName: PersonalMqConfig
// * @Description: 个人MQ配置
// * @Author: liangxin
// * @CreateDate: 2019/7/2 16:04
// * @UpdateDate: 2019/7/2 16:04
// */
//@Configuration
//public class PersonalMqConfig {
//
//    /**
//     * 创建直连交换机
//     *
//     * @return
//     */
//    @Bean
//    public DirectExchange productExchange() {
//        // 交换机不持久化，不自动删除
//        return new DirectExchange(RabbitMqExchange.TEST_EXCHANGE_2, false, false);
//    }
//
//    /**
//     * 创建队列
//     *
//     * @return
//     */
//    @Bean
//    public Queue productFmsSkuQueue() {
//        return new Queue(Queues.TEST_QUEUE);
//    }
//
//    /**
//     * 绑定队列
//     * @return
//     */
//    @Bean
//    public Binding bindingQueue() {
//        return new Binding(Queues.TEST_QUEUE, Binding.DestinationType.QUEUE, RabbitMqExchange.TEST_EXCHANGE_2, Queues.TEST_ROUTING_KEY, null);
//    }
//
//}
