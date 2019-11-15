package com.fun.business.sharon.biz.business.vo;

import com.fun.business.sharon.common.Page;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: CustomerInfoSearchVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/11/15 14:47
 * @UpdateDate: 2019/11/15 14:47
 */
@Data
public class CustomerInfoSearchVo extends Page {

    private String fullName;

    private String country;

    /**
     * 客户重要等级
     * important | middle | weak
     */
    private String grade;

    /**
     * 客户类型
     * deal
     * undeal
     */
    private String type;

    private Date createAtStart;

    private Date createAtEnd;

}
