package com.fun.business.sharon.biz.business.service.impl;

import com.fun.business.sharon.biz.business.bean.Product;
import com.fun.business.sharon.biz.business.dao.ProductMapper;
import com.fun.business.sharon.biz.business.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-07-01
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
