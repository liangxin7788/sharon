package com.fun.business.sharon.common;

import lombok.Data;

/**
 * @Package: com.fun.business.sharon.common
 * @ClassName: Page
 * @Description: 公共分页对象
 * @Author: liangxin
 * @CreateDate: 2019/10/24 18:08
 * @UpdateDate: 2019/10/24 18:08
 */
@Data
public class Page {

    /**
     * 当前页
     */
    private int pageIndex;

    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 总数
     */
    private int total;

}
