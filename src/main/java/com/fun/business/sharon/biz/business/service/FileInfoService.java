package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.vo.FileSearchVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
public interface FileInfoService extends IService<FileInfo> {

    IPage<FileInfo> getFileInfoList(FileSearchVo vo);

}
