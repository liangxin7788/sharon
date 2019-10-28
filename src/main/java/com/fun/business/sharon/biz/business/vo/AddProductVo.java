package com.fun.business.sharon.biz.business.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: AddProductVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/10/28 14:44
 * @UpdateDate: 2019/10/28 14:44
 */
@Data
public class AddProductVo {

    /**
     * 产品记录id，没传就是新增
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 备注信息
     */
    private String description;

    /**
     * 型号
     */
    private String model;

    /**
     * 材质
     */
    private String material;

    /**
     * 应用领域
     */
    private String applyTo;

    /**
     * 主图
     */
    private MultipartFile[] firstImage;

    /**
     * 关联图
     */
    private MultipartFile[] referenceImages;

}
