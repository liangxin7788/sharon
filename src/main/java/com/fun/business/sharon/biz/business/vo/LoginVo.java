package com.fun.business.sharon.biz.business.vo;

import lombok.Data;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: LoginVo
 * @Description: 登录参数封装对象
 * @Author: liangxin
 * @CreateDate: 2019/6/20 14:16
 * @UpdateDate: 2019/6/20 14:16
 */
@Data
public class LoginVo {

    private String userName;

    private String password;

    private Boolean rememberMe;

}
