package com.fun.business.sharon.biz.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     客户留言墙
 * </p>
 *
 * @author liangxin
 * @since 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CustomerMassage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户所属国家
     */
    private String comeFrom;

    /**
     * 留言内容
     */
    private String massage;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 客户邮箱
     */
    private String customerEmail;

    /**
     * 公司
     */
    private String company;

    /**
     * 创建时间 虽然全局配置了时间格式，此处是为了发送邮件的时候格式处理
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;

}
