package com.fun.business.sharon.biz.business.vo;

import lombok.Data;

import java.util.List;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: DelFileVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/10/25 11:26
 * @UpdateDate: 2019/10/25 11:26
 */
@Data
public class DelFileVo {

    private List<Integer> fileIds;

    /**
     * 删除备注
     */
    private String remark;

}
