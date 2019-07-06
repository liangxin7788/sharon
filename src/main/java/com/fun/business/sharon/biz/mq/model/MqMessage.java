package com.fun.business.sharon.biz.mq.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息模型
 * 
 * @author Kevin
 *
 */
@Data
public class MqMessage implements Serializable {
    private static final long serialVersionUID = -2885649815878293661L;

    /**
     * 消息类型
     */
    private MqType mqType;

    /**
     * 服务名
     */
    private String service;

    /**
     * 分为系统和用户创建，系统名为System
     */
    private String sender;

    /**
     * 接收者
     */
    private String recipient;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 主题
     */
    private String topic;

    /**
     * 消息内容
     */
    private String body;

    /**
     * 参数，为body对应的字符串的参数
     */
    private Object[] args;

    /**
     * 是否保存
     */
    private boolean isSave = true;

    public MqMessage() {
        this.createTime = new Date();
    }

    /**
     * 直接创建对象
     * 
     * @param isSave 是否保存
     * @param mqType 消息类型
     * @param service 服务
     * @param sender 发送者
     * @param recipient 接收者
     * @param topic 主题
     * @param body 内容
     * @param args 参数
     */
    public MqMessage(boolean isSave, MqType mqType, String service, String sender, String recipient, String topic,
                     String body, Object[] args) {
        this.isSave = isSave;
        this.mqType = mqType;
        this.service = service;
        this.sender = sender;
        this.recipient = recipient;
        this.topic = topic;
        this.body = body;
        this.args = args;
        this.createTime = new Date();
    }

    /**
     * 将body转换为指定的参数模型，便于以后业务扩展
     * 
     * @param typeRefer type类型
     * @return R
     */
    public <R> R getBodyValue(TypeReference<R> typeRefer) {
        if (body == null || typeRefer == null) {
            return null;
        }

        return JSON.parseObject(body, typeRefer);
    }
}
