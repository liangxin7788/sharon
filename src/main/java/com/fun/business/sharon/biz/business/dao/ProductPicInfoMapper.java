package com.fun.business.sharon.biz.business.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.ProductPicInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2019-10-28
 */
@Repository
public interface ProductPicInfoMapper extends BaseMapper<ProductPicInfo> {

    List<ProductPicInfo> selectProductPicInfoByProductId(Integer id);

}
