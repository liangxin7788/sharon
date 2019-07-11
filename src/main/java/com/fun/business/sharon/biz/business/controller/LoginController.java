package com.fun.business.sharon.biz.business.controller;

import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.service.UserService;
import com.fun.business.sharon.biz.business.vo.LoginVo;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.fun.business.sharon.biz.business.controller
 * @ClassName: LoginController
 * @Description: 登录相关接口
 * @Author: liangxin
 * @CreateDate: 2019/6/20 14:10
 * @UpdateDate: 2019/6/20 14:10
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String  getIndex(){
        return "index";
    }

    @PostMapping( {"/login", "/"} )
    @ApiOperation(value = "登录")
    public GlobalResult<?> login(@RequestBody LoginVo vo, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        if (StringUtil.isNotEmpty(vo.getUserName())) { // liangxin + 1 = e6f799c516ef2b6e1b54b960ad462e0a
            User user = userService.findByUserName(vo.getUserName());
            if (ObjectUtil.isNotEmpty(user)) {
                map.put("user", user);
                map.put("token", user);
            }
        }
        return GlobalResult.newSuccess();
    }

}
