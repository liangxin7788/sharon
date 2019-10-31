package com.fun.business.sharon.biz.business.dao;

import com.fun.business.sharon.biz.business.bean.CustomerMassage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.vo.MassageSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2019-10-31
 */
@Repository
public interface CustomerMassageMapper extends BaseMapper<CustomerMassage> {

    int getMassageListCount(@Param("vo")MassageSearchVo vo);

    List<CustomerMassage> getMassageList(@Param("vo") MassageSearchVo vo, int offset, int pageSize);
}
