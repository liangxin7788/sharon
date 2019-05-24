package com.fun.business.sharon.biz.personal.service.impl;

import com.fun.business.sharon.biz.personal.bean.User;
import com.fun.business.sharon.biz.personal.dao.UserMapper;
import com.fun.business.sharon.biz.personal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-05-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
