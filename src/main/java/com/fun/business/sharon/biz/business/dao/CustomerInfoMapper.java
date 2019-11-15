package com.fun.business.sharon.biz.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.CustomerInfo;
import com.fun.business.sharon.biz.business.vo.CustomerInfoSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2019-11-15
 */
@Repository
public interface CustomerInfoMapper extends BaseMapper<CustomerInfo> {

    List<CustomerInfo> selectCustomerInfoList(@Param("vo") CustomerInfoSearchVo vo, int offset, int pageSize);

    Integer selectCustomerInfoListCount(@Param("vo")CustomerInfoSearchVo vo);
}
