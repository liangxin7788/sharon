package com.fun.business.sharon.biz.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.business.sharon.biz.business.bean.CustomerInfo;
import com.fun.business.sharon.biz.business.dao.CustomerInfoMapper;
import com.fun.business.sharon.biz.business.service.CustomerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.vo.CustomerInfoImportVo;
import com.fun.business.sharon.biz.business.vo.CustomerInfoSearchVo;
import com.fun.business.sharon.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-11-15
 */
@Service
@Slf4j
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public IPage<CustomerInfo> getCustomerInfoList(CustomerInfoSearchVo vo) {
        int pageIndex = vo.getPageIndex();
        int pageSize = vo.getPageSize();

        pageIndex = pageIndex ==0? 1 : pageIndex;
        pageSize = pageSize ==0? 10 : pageSize;
        int offset = (pageIndex - 1) * pageSize;

        IPage<CustomerInfo> page = new Page<>(pageIndex, pageSize);
        Integer total = customerInfoMapper.selectCustomerInfoListCount(vo);
        List<CustomerInfo> list = customerInfoMapper.selectCustomerInfoList(vo, offset, pageSize);

        page.setTotal(total);
        page.setRecords(list);
        page.setCurrent(pageIndex);
        page.setPages(pageSize);

        return page;
    }

    @Override
    public Integer addCustomerInfo(CustomerInfo customerInfo) {
        log.info("更新或添加客户信息 " + JSON.toJSONString(customerInfo) + " 时间： " + new Date());
        Integer id = customerInfo.getId();
        customerInfo.setUpdateAt(new Date());
        if (ObjectUtil.isNotEmpty(id)) {
            return customerInfoMapper.updateById(customerInfo);
        }else {
            customerInfo.setCreateAt(new Date());
        }
        return customerInfoMapper.insert(customerInfo);
    }

    @Override
    public Integer importCustomerInfo(List<CustomerInfoImportVo> customerInfoImportVos) {
        if (null != customerInfoImportVos && customerInfoImportVos.size() > 0) {
            customerInfoImportVos.forEach( t->{
                CustomerInfo customerInfo = new CustomerInfo();
                BeanUtils.copyProperties(t, customerInfo);
                customerInfo.setCreateAt(new Date());
                customerInfo.setUpdateAt(new Date());
                customerInfoMapper.insert(customerInfo);
            });
        }
        return 1;
    }
}
