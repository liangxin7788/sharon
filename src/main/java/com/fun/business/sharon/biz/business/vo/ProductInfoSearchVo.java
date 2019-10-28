package com.fun.business.sharon.biz.business.vo;

import com.fun.business.sharon.common.Page;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: ProductInfoSearchVo
 * @Description: 产品信息列表查询vo
 * @Author: liangxin
 * @CreateDate: 2019/10/28 12:03
 * @UpdateDate: 2019/10/28 12:03
 */
@Data
public class ProductInfoSearchVo extends Page {

    /**
     * 产品创建开始时间
     */
    private Date createAt;

    /**
     * 产品创建截止时间
     */
    private Date createEnd;

    /**
     * 产品名称like
     */
    private String productName;

    /**
     * 产品描述like
     */
    private String productDescription;

    /**
     * 产品材质，下拉框多选
     */
    private List<String> materials;

}
