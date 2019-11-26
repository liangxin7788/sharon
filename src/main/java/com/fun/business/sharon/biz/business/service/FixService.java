package com.fun.business.sharon.biz.business.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Package: com.fun.business.sharon.biz.business.service
 * @ClassName: FixService
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/11/26 11:04
 * @UpdateDate: 2019/11/26 11:04
 */
public interface FixService {

    void fixTransferOldAndNew(MultipartFile multipartFile);

}
