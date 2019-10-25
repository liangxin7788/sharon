package com.fun.business.sharon.biz.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fun.business.sharon.common.Page;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: fileSearchVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/10/24 18:09
 * @UpdateDate: 2019/10/24 18:09
 */
@Data
public class FileSearchVo extends Page {

    /**
     * 文件上传开始时间
     */
    private Date beginTime;

    /**
     * 文件上传截止时间
     */
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 已经进行了全局配置
    private Date endTime;

    /**
     * 文件名字模糊查询
     */
    private String fileName;

    /**
     * 文件描述信息模糊查询
     */
    private String description;

}
