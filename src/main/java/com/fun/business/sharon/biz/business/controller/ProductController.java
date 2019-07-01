package com.fun.business.sharon.biz.business.controller;


import com.fun.business.sharon.biz.business.bean.Product;
import com.fun.business.sharon.biz.seckill.service.SeckillService;
import com.fun.business.sharon.common.GlobalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-07-01
 */
@RestController
@Api(description = "产品相关")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private SeckillService seckillService;

    @ApiOperation("测试秒杀")
    @GetMapping("/testSeckill")
    public GlobalResult<Product> testSeckill(@ApiParam("产品id")@RequestParam(value = "productId", required = true)Integer productId){

        return GlobalResult.newSuccess();
    }


}
