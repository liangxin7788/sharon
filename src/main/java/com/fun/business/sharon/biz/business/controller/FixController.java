package com.fun.business.sharon.biz.business.controller;

import com.fun.business.sharon.biz.business.service.FixService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Package: com.fun.business.sharon.biz.business.controller
 * @ClassName: FixController
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/11/26 11:04
 * @UpdateDate: 2019/11/26 11:04
 */
@RestController
public class FixController {

    @Autowired
    private FixService fixService;

    @PostMapping("/fixTransferOldAndNew")
    @ApiOperation("修复文案")
    public String fixTransferOldAndNew(MultipartFile multipartFile) throws IOException {
        fixService.fixTransferOldAndNew(multipartFile);
        return "success";
    }

}
