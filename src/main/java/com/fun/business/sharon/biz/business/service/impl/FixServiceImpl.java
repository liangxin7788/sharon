package com.fun.business.sharon.biz.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fun.business.sharon.biz.business.service.FixService;
import com.fun.business.sharon.utils.ExcelPoiUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.fun.business.sharon.biz.business.service.impl
 * @ClassName: FixServiceImpl
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/11/26 11:05
 * @UpdateDate: 2019/11/26 11:05
 */
@Service
@Slf4j
public class FixServiceImpl implements FixService {

//    @Autowired
//    private

    @Override
    public void fixTransferOldAndNew(MultipartFile multipartFile) {

        try {
            List<String[]> strings = ExcelPoiUtil.readExcel(multipartFile);
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            strings.forEach(t -> {
                String userNo = t[3];
                String no = userNo.split("-")[0];
                Integer userId = getUserIdByNo(no);

                String newProduct = " update single_item set translate_emp_id = '" + userId + "', translate_emp_name = '" + t[3] + "' where son_sku = '" + t[1].trim() + "';\r\n ";

//                DevEmpVo devEmpVo = stockkeepingunitMapper.selectUserInfo(no);
//                if (ObjectUtils.isNotEmpty(devEmpVo)) {
//                    String oldProduct = " update stockkeepingunit set salemanagerid = '" + devEmpVo.getUserId() + "', salemanagerno = '" + no + "', salemanagerchinesename = '" + userNo.split("-")[1] + "' where article_number = '" + t[1].trim() + "';\r\n ";
//                    list2.add(oldProduct);
//                }
                list1.add(newProduct);
            });
            FileUtils.writeLines(new File("c://2019//newProduct.txt"), list1);
            FileUtils.writeLines(new File("c://2019//oldProduct.txt"), list2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据产品经理登录号获取新用户信息登录用户id（直接从缓存获取)
     *
     * @param productmanagerno
     * @return
     */
    private Integer getUserIdByNo(String productmanagerno) {
        return IData4.userMap.get(productmanagerno);
    }

}
