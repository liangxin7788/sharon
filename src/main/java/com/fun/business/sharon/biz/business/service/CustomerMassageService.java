package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fun.business.sharon.biz.business.bean.CustomerMassage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.vo.AddMassageVo;
import com.fun.business.sharon.biz.business.vo.MassageSearchVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-10-31
 */
public interface CustomerMassageService extends IService<CustomerMassage> {

    Integer addMassage(AddMassageVo vo);

    IPage<CustomerMassage> getMassageList(MassageSearchVo vo);

}
