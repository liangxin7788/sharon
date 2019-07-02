package com.fun.business.sharon.biz.email;

import lombok.Data;

/**
 * @Package: com.fun.business.sharon.biz.email
 * @ClassName: TextMassageVo
 * @Description: 纯文本邮件输入vo
 * @Author: liangxin
 * @CreateDate: 2019/7/1 17:34
 * @UpdateDate: 2019/7/1 17:34
 */
@Data
public class TextMassageVo {

    /**
     * 接收者
     */
    private String to;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

}
