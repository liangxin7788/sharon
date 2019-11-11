package com.fun.business.sharon.biz.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fun.business.sharon.biz.business.bean.Product;
import com.fun.business.sharon.biz.business.service.ProductService;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductInfoSearchVo;
import com.fun.business.sharon.biz.seckill.service.SeckillService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

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
    private ProductService productService;

    @ApiOperation("获取产品信息列表")
    @PostMapping("/getProductList")
    public GlobalResult<?> getProductList(@RequestBody ProductInfoSearchVo vo){
        IPage<Product> page = null;
        if (ObjectUtil.isNotEmpty(vo)) {
            page = productService.getProductList(vo);
        }
        return GlobalResult.newSuccess(page);
    }

    @ApiOperation("添加/编辑产品信息")
    @PostMapping("/addOrEditProduct")
    public GlobalResult<?> addOrEditProduct(AddProductVo productVo){
        int result = 0;
        if (ObjectUtil.isNotEmpty(productVo)) {
            result = productService.addOrEditProduct(productVo);
        }
        return GlobalResult.newSuccess(result);
    }

    @ApiOperation("停售某些产品")
    @DeleteMapping("/delProduct")
    public GlobalResult<?> delProduct(@RequestParam(value = "productIds") List<Integer> productIds){
        return GlobalResult.newSuccess(productService.delProduct(productIds));
    }

    @ApiOperation("获取产品详情")
    @GetMapping("/getDetailById")
    public GlobalResult<?> getDetailById(@RequestParam(value = "productId") Integer productId){
        return GlobalResult.newSuccess(productService.getDetailById(productId));
    }

    @ApiOperation("测试秒杀")
    @GetMapping("/testSeckill")
    public GlobalResult<Product> testSeckill(@ApiParam("产品id")@RequestParam(value = "productId", required = true)Integer productId){

        return GlobalResult.newSuccess();
    }

}
