package com.fun.business.sharon.biz.email;

import lombok.Data;

import java.util.List;

/**
 * @Package: com.fun.business.sharon.biz.email
 * @ClassName: TextMassageVo
 * @Description: 带附件邮件输入vo
 * @Author: liangxin
 * @CreateDate: 2019/7/1 17:34
 * @UpdateDate: 2019/7/1 17:34
 */
@Data
public class FileMassageVo {

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

    /**
     * 附件位置
     */
    private String filePath1;
    private String filePath2;

    private List<String> filePaths;

}
