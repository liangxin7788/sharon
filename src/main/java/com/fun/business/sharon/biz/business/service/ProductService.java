package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fun.business.sharon.biz.business.bean.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductInfoSearchVo;
import com.fun.business.sharon.common.GlobalResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-07-01
 */
public interface ProductService extends IService<Product> {

    IPage getProductList(ProductInfoSearchVo vo);

    int addOrEditProduct(AddProductVo productVo);

    Integer delProduct(List<Integer> productIds);
}
