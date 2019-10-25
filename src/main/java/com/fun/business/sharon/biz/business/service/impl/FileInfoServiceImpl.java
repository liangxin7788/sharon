package com.fun.business.sharon.biz.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.dao.FileInfoMapper;
import com.fun.business.sharon.biz.business.service.FileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.vo.FileSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public IPage<FileInfo> getFileInfoList(FileSearchVo vo) {

        int pageIndex = vo.getPageIndex();
        int pageSize = vo.getPageSize();

        if (pageIndex == 0) pageIndex = 1;
        if (pageSize == 0) pageSize = 10;
        int offset = (pageIndex - 1) * pageSize;


        Page<FileInfo> infoPage = new Page<>(pageIndex, pageSize);
        int total = fileInfoMapper.getFileInfoListCount(vo);
        List<FileInfo> infoList = fileInfoMapper.getFileInfoList(vo, offset, pageSize);

        infoPage.setTotal(total);
        infoPage.setRecords(infoList);
        infoPage.setCurrent(pageIndex);
        infoPage.setPages(pageSize);

        return infoPage;
    }
}
