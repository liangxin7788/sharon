package com.fun.business.sharon.biz.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.CustomerMassage;
import com.fun.business.sharon.biz.business.dao.CustomerMassageMapper;
import com.fun.business.sharon.biz.business.service.CustomerMassageService;
import com.fun.business.sharon.biz.business.vo.AddMassageVo;
import com.fun.business.sharon.biz.business.vo.MassageSearchVo;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2019-10-31
 */
@Service
@Slf4j
public class CustomerMassageServiceImpl extends ServiceImpl<CustomerMassageMapper, CustomerMassage> implements CustomerMassageService {

    @Autowired
    private CustomerMassageMapper customerMassageMapper;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public Integer addMassage(AddMassageVo vo) {
        CustomerMassage customerMassage = new CustomerMassage();
        customerMassage.setName(vo.getName());
        customerMassage.setComeFrom(vo.getComeFrom());
        customerMassage.setMassage(vo.getMassage());
        customerMassage.setPhoneNumber(vo.getPhoneNumber());
        customerMassage.setCustomerEmail(vo.getCustomerEmail());
        customerMassage.setCompany(vo.getCompany());
        customerMassage.setCreateAt(new Date());
        try {
            mailUtil.send(customerMassage, "客户线上留言");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new OperateException("发送邮件失败！" + JSON.toJSONString(vo));
        }
        return customerMassageMapper.insert(customerMassage);
    }

    @Override
    public IPage<CustomerMassage> getMassageList(MassageSearchVo vo) {
        int pageIndex = vo.getPageIndex();
        int pageSize = vo.getPageSize();
        pageIndex = pageIndex == 0 ? 1 : pageIndex;
        pageSize = pageSize == 0 ? 10 : pageSize;
        int offset = (pageIndex - 1) * pageSize;

        IPage<CustomerMassage> page = new Page(pageIndex, pageSize);

        int total = customerMassageMapper.getMassageListCount(vo);
        List<CustomerMassage> list = customerMassageMapper.getMassageList(vo, offset, pageSize);

        page.setCurrent(pageIndex);
        page.setRecords(list);
        page.setTotal(total);
        page.setPages(pageSize);
        return page;
    }
}
