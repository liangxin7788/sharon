package com.fun.business.sharon.biz.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: AddMassageVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/10/31 17:03
 * @UpdateDate: 2019/10/31 17:03
 */
@Data
public class AddMassageVo {

    @ApiModelProperty("客户名称")
    private String name;

    @ApiModelProperty("客户所属国家")
    private String comeFrom;

    @ApiModelProperty("留言内容")
    private String massage;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("客户邮箱")
    private String customerEmail;

    @ApiModelProperty("公司名称")
    private String company;

}
