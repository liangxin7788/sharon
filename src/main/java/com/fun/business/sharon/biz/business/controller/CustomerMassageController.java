package com.fun.business.sharon.biz.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fun.business.sharon.biz.business.bean.CustomerMassage;
import com.fun.business.sharon.biz.business.service.CustomerMassageService;
import com.fun.business.sharon.biz.business.vo.AddMassageVo;
import com.fun.business.sharon.biz.business.vo.MassageSearchVo;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.CheckParamUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/customerMassage")
public class CustomerMassageController {

    @Autowired
    private CustomerMassageService customerMassageService;

    @PostMapping("/addMassage")
    @ApiOperation("增加一条留言信息")
    public GlobalResult<?> addMassage(@RequestBody AddMassageVo vo){
        // 反射进行必填校验
        CheckParamUtil.checkParamForCommit(vo, new String[]{"name", "massage", "customerEmail"});
        return GlobalResult.newSuccess(customerMassageService.addMassage(vo));
    }

    @PostMapping("/getMassageList")
    @ApiOperation("获取留言墙信息")
    public GlobalResult<?> getMassageList(@RequestBody MassageSearchVo vo){
        IPage<CustomerMassage> page = null;
        page = customerMassageService.getMassageList(vo);
        return GlobalResult.newSuccess(page);
    }

}
