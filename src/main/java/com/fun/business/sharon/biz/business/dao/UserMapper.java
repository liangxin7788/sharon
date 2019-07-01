package com.fun.business.sharon.biz.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE user_name = #{ userName }")
    User findByUserName(@Param("userName") String userName);
}
