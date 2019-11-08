package com.fun.business.sharon.biz.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    int getUserListCount(@Param("vo") UserVo vo);

    List<User> getUserList(@Param("vo") UserVo vo, int offset, int pageSize);

}
