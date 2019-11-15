package com.fun.business.sharon.biz.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangxin
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CustomerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户简称
     */
    private String nickName;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 国家
     */
    private String country;

    /**
     * 公司名
     */
    private String company;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 重要等级
     */
    private String grade;

    /**
     * 客户类型
     */
    private String type;

    /**
     * 行业类型
     */
    private String industry;

    /**
     * 最后联系时间
     */
    private Date lastContactTime;

    /**
     * 主营产品
     */
    private String mainProducts;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 最后更新时间
     */
    private Date updateAt;


}
