package com.fun.business.sharon.biz.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fun.business.sharon.biz.business.bean.CustomerInfo;
import com.fun.business.sharon.biz.business.service.CustomerInfoService;
import com.fun.business.sharon.biz.business.vo.CustomerInfoImportVo;
import com.fun.business.sharon.biz.business.vo.CustomerInfoSearchVo;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.CheckParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-11-15
 */
@RestController
@RequestMapping("/customerInfo")
@Api(description = "客户信息管理")
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @PostMapping("/getCustomerInfoList")
    @ApiOperation("获取客户信息列表")
    public GlobalResult<?> getCustomerInfoList(@RequestBody CustomerInfoSearchVo vo){
        IPage<CustomerInfo> page = customerInfoService.getCustomerInfoList(vo);
        return GlobalResult.newSuccess(page);
    }

    @PostMapping("/addCustomerInfo")
    @ApiOperation("添加客户信息")
    public GlobalResult<?> addCustomerInfo(@RequestBody CustomerInfo customerInfo){
        CheckParamUtil.checkParamForCommit(customerInfo, new String[]{"nickName", "email", "grade", "type"});
        Integer result = customerInfoService.addCustomerInfo(customerInfo);
        return GlobalResult.newSuccess(result);
    }

    @PostMapping("/importCustomerInfo")
    @ApiOperation("导入客户信息")
    public GlobalResult<?> importCustomerInfo(@RequestBody List<CustomerInfoImportVo> customerInfoImportVos){
        Integer result = customerInfoService.importCustomerInfo(customerInfoImportVos);
        if (null != result && result != 0) {
            return GlobalResult.newSuccess("导入成功!");
        }else {
            return GlobalResult.newError("导入失败！");
        }
    }

}
