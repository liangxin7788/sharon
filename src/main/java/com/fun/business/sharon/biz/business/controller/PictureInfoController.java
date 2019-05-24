package com.fun.business.sharon.biz.business.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fun.business.sharon.biz.business.bean.PictureInfo;
import com.fun.business.sharon.biz.business.dao.PictureInfoMapper;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.RedisUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-05-20
 */
@Slf4j
@RestController
@RequestMapping("/pictureInfo")
public class PictureInfoController {

	@Autowired
	private PictureInfoMapper pictureInfoMapper;
	
	@GetMapping("/testSelect")
	@ApiOperation("测试缓存")
	public GlobalResult<?> testSelect() {
		String data = RedisUtil.getValueFromRedis("testSelect");
		PictureInfo info = JSON.parseObject(data, PictureInfo.class);
		if(ObjectUtil.isNotEmpty(info)) {
			return GlobalResult.newSuccess(info);
		}
		List<PictureInfo> list = pictureInfoMapper.selectList(null);
		RedisUtil.setValueToRedis("testSelect", list.get(0));
		for (PictureInfo pictureInfo : list) {
			String introduce = pictureInfo.getPictureIntroduce();
			log.info(introduce);
			log.debug(introduce);
			System.out.println(introduce);
		}
		return GlobalResult.newSuccess(list.get(0));
	}
	
}
