package com.fun.business.sharon.biz.business.service.impl;

import com.fun.business.sharon.biz.business.bean.Order;
import com.fun.business.sharon.biz.business.dao.OrderMapper;
import com.fun.business.sharon.biz.business.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
