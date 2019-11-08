package com.fun.business.sharon.biz.business.controller;


import com.fun.business.sharon.biz.business.bean.MaterialType;
import com.fun.business.sharon.biz.business.service.MaterialTypeService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.CheckParamUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/materialType")
public class MaterialTypeController {

    @Autowired
    private MaterialTypeService materialTypeService;

    @GetMapping("/list")
    @ApiOperation("获取所有材质类型")
    public GlobalResult<?> list(){
        List<MaterialType> list = materialTypeService.list(null);
        return GlobalResult.newSuccess(list);
    }

    @PostMapping("/addMaterialType")
    @ApiOperation("添加材质类型")
    public GlobalResult<?> addMaterialType(@RequestBody MaterialType material){
        CheckParamUtil.checkParamForCommit(material, new String[]{"cnName", "enName"});
        MaterialType materialType = new MaterialType();
        materialType.setCnName(material.getCnName());
        materialType.setEnName(material.getEnName());
        materialType.setCreateAt(new Date());
        materialType.setUpdateAt(new Date());
        return GlobalResult.newSuccess(materialTypeService.save(materialType));
    }

    @DeleteMapping("/delMaterialType")
    @ApiOperation("删除材质类型")
    public GlobalResult<?> delMaterialType(@RequestParam(value = "materialId", required = true)Integer materialId){
        return GlobalResult.newSuccess(materialTypeService.removeById(materialId));
    }

}
