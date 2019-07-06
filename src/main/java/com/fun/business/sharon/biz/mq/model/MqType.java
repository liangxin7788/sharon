package com.fun.business.sharon.biz.mq.model;

/***
 * 消息类型枚举
 * 
 * @author Kevin
 *
 */
public enum MqType {
    /**
     * 正常
     */
    INFO,
    /**
     * 警告
     */
    WARN,
    /**
     * 错误
     */
    ERROR,
    /**
     * 忽略
     */
    IGNORE;
}
