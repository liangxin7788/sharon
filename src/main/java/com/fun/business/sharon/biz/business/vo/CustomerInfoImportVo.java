package com.fun.business.sharon.biz.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: CustomerInfoImportVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/11/15 16:33
 * @UpdateDate: 2019/11/15 16:33
 */
@Data
public class CustomerInfoImportVo implements Serializable {

    private static final long serialVersionUID = -739770143124209267L;

    @ApiModelProperty("客户简称")
    private String nickName;

    @ApiModelProperty("客户全名")
    private String fullName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("公司名")
    private String company;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("重要等级")
    private String grade;

    @ApiModelProperty("客户类型")
    private String type;

    @ApiModelProperty("行业类型")
    private String industry;

    @ApiModelProperty("主营产品")
    private String mainProducts;

    @ApiModelProperty("最后联系时间")
    private Date lastContactTime;

}
