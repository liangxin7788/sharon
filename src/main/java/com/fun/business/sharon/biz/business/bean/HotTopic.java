package com.fun.business.sharon.biz.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HotTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 新闻标题
     */
    private String newsTitle;

    /**
     * 链接
     */
    private String newsLink;

    /**
     * 当前浏览数
     */
    private Integer number;

    /**
     * 上升/下降
     */
    private Boolean trend;

    /**
     * 录入时间
     */
    private Date loadAt;
    
}
