package com.fun.business.sharon.biz.business.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.Product;
import com.fun.business.sharon.biz.business.vo.ProductInfoSearchVo;
import org.apache.ibatis.annotations.Param;
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
public interface ProductMapper extends BaseMapper<Product> {

    int getProductListCount(@Param("vo") ProductInfoSearchVo vo);

    List<Product> getProductList(@Param("vo") ProductInfoSearchVo vo, int offset, int pageSize);
}
