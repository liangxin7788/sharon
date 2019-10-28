package com.fun.business.sharon.biz.seckill.service.impl;

import com.fun.business.sharon.biz.business.bean.Order;
import com.fun.business.sharon.biz.business.bean.Product;
import com.fun.business.sharon.biz.business.dao.OrderMapper;
import com.fun.business.sharon.biz.business.dao.ProductMapper;
import com.fun.business.sharon.biz.seckill.RedisLock;
import com.fun.business.sharon.biz.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.fun.business.sharon.biz.seckill.service.impl
 * @ClassName: SeckillServiceImpl
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/7/1 11:12
 * @UpdateDate: 2019/7/1 11:12
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

//    @Override
//    @Transactional
//    public Product seckill(Integer id) throws RuntimeException {
//        //加锁
//        long time = System.currentTimeMillis() + 1000*10;  //超时时间：10秒，最好设为常量
//
//        boolean isLock = redisLock.lock(String.valueOf(id), String.valueOf(time));
//        if(!isLock){
//            throw new RuntimeException("人太多了，换个姿势再试试~");
//        }
//        //查库存
//        Product product = productMapper.selectById(id);
//        if(product.getStock()==0) throw new RuntimeException("已经卖光");
//        //写入订单表
//        Order order=new Order();
//        order.setProductId(product.getId());
//        order.setProductName(product.getName());
//        orderMapper.insert(order);
//        //减库存
//        product.setPrice(null);
//        product.setName(null);
//        product.setStock(product.getStock()-1);
//        productMapper.updateById(product);
//        //解锁
//        redisLock.unlock(String.valueOf(id),String.valueOf(time));
//        return product;
//    }
}
