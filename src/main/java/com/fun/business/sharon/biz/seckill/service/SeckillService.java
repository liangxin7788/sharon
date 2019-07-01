package com.fun.business.sharon.biz.seckill.service;

import com.fun.business.sharon.biz.business.bean.Product;

/**
 * @Package: com.fun.business.sharon.biz.seckill.service
 * @ClassName: SeckillService
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/7/1 11:11
 * @UpdateDate: 2019/7/1 11:11
 */
public interface SeckillService {

    Product seckill(Integer id)throws RuntimeException;

}
