package com.fun.business.sharon.biz.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.dao.FileInfoMapper;
import com.fun.business.sharon.biz.business.service.FileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.vo.DelFileVo;
import com.fun.business.sharon.biz.business.vo.EditFileInfoVo;
import com.fun.business.sharon.biz.business.vo.FileSearchVo;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @Override
    public Integer editFileInfo(EditFileInfoVo vo) {
        if (ObjectUtil.isNotEmpty(vo)) {
            Integer fileId = vo.getFileId();
            if (fileId == null) {
                throw new OperateException("修改记录id未传！");
            }
            FileInfo fileInfo = fileInfoMapper.selectById(fileId);

            StringBuffer logBuffer = new StringBuffer("修改记录：" + fileId);

            if (StringUtil.isNotEmpty(vo.getFileName())) {
                fileInfo.setFileName(vo.getFileName());
                logBuffer.append("文件名修改为：" + vo.getFileName());
            }

            if (StringUtil.isNotEmpty(vo.getDescription())) {
                fileInfo.setFileIntroduce(vo.getDescription());
                logBuffer.append(" 文件描述修改为：" + vo.getDescription());
            }
            log.info(logBuffer.toString());
            return fileInfoMapper.updateById(fileInfo);
        }
        return 0;
    }

    @Override
    public Integer delFile(DelFileVo vo) {
        if (ObjectUtil.isEmpty(vo.getFileIds())) {
            throw new OperateException("请选择您要删除的记录！");
        }
        if (StringUtil.isEmpty(vo.getRemark())) {
            throw new OperateException("请填写删除备注信息！");
        }
        try {
            List<FileInfo> infoList = fileInfoMapper.selectBatchIds(vo.getFileIds());
            if (ObjectUtil.isNotEmpty(infoList)) {
                infoList.forEach( file -> {
                    log.info("文件被删除 " + file.getFileName() + " 备注信息：" + vo.getRemark());
                    fileInfoMapper.deleteById(file.getId());
                });
            }
            return 1;
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
