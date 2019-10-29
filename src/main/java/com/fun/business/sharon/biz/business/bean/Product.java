package com.fun.business.sharon.biz.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author liangxin
 * @since 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 备注信息
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 型号
     */
    private String model;

    /**
     * 材质
     */
    private String material;

    /**
     * 应用领域
     */
    private String applyTo;

    /**
     * 系统中唯一标识字段
     */
    private String unitTag;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    /**
     * 数据库不做映射
     */
    @TableField(exist = false)
    private ProductPicInfo productPicInfo;

}
