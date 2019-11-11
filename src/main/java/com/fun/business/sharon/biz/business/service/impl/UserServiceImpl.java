package com.fun.business.sharon.biz.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.business.sharon.biz.business.vo.UserVo;
import com.fun.business.sharon.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.dao.UserMapper;
import com.fun.business.sharon.biz.business.service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public IPage<User> getUserList(UserVo vo) {
        int pageIndex = vo.getPageIndex();
        int pageSize = vo.getPageSize();
        pageIndex = pageIndex == 0?1:pageIndex;
        pageSize = pageSize == 0?10:pageSize;
        int offset = (pageIndex - 1) * pageSize;
        Page<User> page = new Page<>(pageIndex, pageSize);
        int total = userMapper.getUserListCount(vo);
        List<User> list = userMapper.getUserList(vo, offset, pageSize);

        page.setTotal(total);
        page.setRecords(list);
        page.setCurrent(pageIndex);
        page.setSize(pageSize);

        return page;
    }

    @Override
    public Boolean judgeUser(String userName, String password) {
        User user = findByUserName(userName);
        if (null != user) {
            return user.getPassword().equals(DigestUtils.md5Hex(password));
        }
        return false;
    }

    @Override
    public User judgeArleadyHasUser(String userName) {
        return findByUserName(userName);
    }
}
