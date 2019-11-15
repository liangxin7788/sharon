package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.CustomerInfo;
import com.fun.business.sharon.biz.business.vo.CustomerInfoImportVo;
import com.fun.business.sharon.biz.business.vo.CustomerInfoSearchVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-11-15
 */
public interface CustomerInfoService extends IService<CustomerInfo> {

    IPage<CustomerInfo> getCustomerInfoList(CustomerInfoSearchVo vo);

    Integer addCustomerInfo(CustomerInfo customerInfo);

    Integer importCustomerInfo(List<CustomerInfoImportVo> customerInfoImportVos);
}
