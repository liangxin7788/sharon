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
 * @since 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductPicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应产品id
     */
    private Integer productId;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 是否是主图；1-是，0-否
     */
    private Integer isFirstImage;

    /**
     * 图片url
     */
    private String imageUrl;


}
