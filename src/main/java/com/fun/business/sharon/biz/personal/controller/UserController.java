package com.fun.business.sharon.biz.personal.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.business.sharon.biz.personal.bean.User;
import com.fun.business.sharon.biz.personal.dao.UserMapper;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-05-21
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/testSelect")
	@ApiOperation("测试数据库连接")
	public String testSelect() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("groupname", "客服一组");
		map.put("inactive", 1);
		
		List<User> list = userMapper.selectByMap(map);
		if (list.size() > 10) {
			list = list.subList(0, 10);
		}
		for (User user : list) {
			String introduce = user.getUsername();
			log.info(introduce);
			log.debug(introduce);
			System.out.println(introduce);
		}
		return "测试成功";
	}
	
	
}
