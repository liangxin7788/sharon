package com.fun.business.sharon.biz.business.vo;

import com.fun.business.sharon.common.Page;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: MassageSearchVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/10/31 19:02
 * @UpdateDate: 2019/10/31 19:02
 */
@Data
public class MassageSearchVo extends Page {

    /**
     * 发送开始时间
     */
    private Date startTime;

    /**
     * 发送截止时间
     */
    private Date endTime;

    /**
     * 国家
     */
    private String country;

    /**
     * 用户名，模糊查询
     */
    private String name;

}
