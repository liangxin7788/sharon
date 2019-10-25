package com.fun.business.sharon.biz.business.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.ObjectUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/remind")
@Slf4j
public class RemindController {

    @PostMapping("/testMapParam")
    @ApiOperation("测试map套接map传参")
    public GlobalResult<?> testMapParam(@RequestBody Map<String, Object> map){
        Object value =  map.get("target");
        Map maps = JSON.parseObject(value.toString(), Map.class);
        Object result = null;
        if (ObjectUtil.isNotEmpty(maps)) {
            result = maps.get("ipv4_net");
            System.out.println(result.toString());
        }
        return GlobalResult.newSuccess("测试返回结果！" + result);
    }
//    public GlobalResult<?> testMapParam(@RequestBody Map<String, Map<String, Object>> map){
//        Map<String, Object> testMap = map.get("test");
//        Object value = testMap.get("key");
//        String list = value.toString();
//        List<String> results = JSON.parseArray(list, String.class);
//        return GlobalResult.newSuccess("测试返回结果！" + results);
//    }

}
