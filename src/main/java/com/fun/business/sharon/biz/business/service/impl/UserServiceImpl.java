package com.fun.business.sharon.biz.business.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.dao.UserMapper;
import com.fun.business.sharon.biz.business.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
