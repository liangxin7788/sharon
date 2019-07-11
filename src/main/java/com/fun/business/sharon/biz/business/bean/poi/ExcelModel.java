package com.fun.business.sharon.biz.business.bean.poi;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.fun.business.sharon.biz.business.bean.poi
 * @ClassName: ExcelModel
 * @Description: excel生成测试模型类
 * @Author: liangxin
 * @CreateDate: 2019/7/11 15:40
 * @UpdateDate: 2019/7/11 15:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelModel extends BaseRowModel {

    @ExcelProperty(value = "姓名", index = 0)
    private String userName;

    @ExcelProperty(value = "地址", index = 1)
    private String address;

    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;

    @ExcelProperty(value = "邮箱", index = 3)
    private String email;

}
