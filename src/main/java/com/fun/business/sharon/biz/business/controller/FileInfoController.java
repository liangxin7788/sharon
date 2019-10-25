package com.fun.business.sharon.biz.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.service.FileInfoService;
import com.fun.business.sharon.biz.business.vo.DelFileVo;
import com.fun.business.sharon.biz.business.vo.EditFileInfoVo;
import com.fun.business.sharon.biz.business.vo.FileSearchVo;
import com.fun.business.sharon.common.GlobalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  器
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@RestController
@Slf4j
@RequestMapping("/pictureInfo")
@Api(description = "文件信息相关")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping("/getFileInfoList")
    @ApiOperation("获取文件所有信息列表")
    public GlobalResult<?> getFileInfoList(@RequestBody FileSearchVo vo){
        IPage<FileInfo> page = fileInfoService.getFileInfoList(vo);
//        log.info(JSONObject.toJSONString(page));
        return GlobalResult.newSuccess(page);
    }

    @PostMapping("/editFileInfo")
    @ApiOperation("编辑文件信息")
    public GlobalResult<?> editFileInfo(@RequestBody EditFileInfoVo vo){
        Integer result = fileInfoService.editFileInfo(vo);
        return GlobalResult.newSuccess(result);
    }

    @DeleteMapping("/delFile")
    @ApiOperation("删除文件")
    public GlobalResult<?> delFile(@RequestBody DelFileVo vo){
        Integer result = fileInfoService.delFile(vo);
        return GlobalResult.newSuccess(result);
    }

}
