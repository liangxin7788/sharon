package com.fun.business.sharon.biz.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangxin
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理产品表id
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 创建用户id
     */
    private Integer createUserId;

    /**
     * 订单创建时间
     */
    private LocalDateTime createAt;

    /**
     * 订单更新时间
     */
    private LocalDateTime updateAt;


}
