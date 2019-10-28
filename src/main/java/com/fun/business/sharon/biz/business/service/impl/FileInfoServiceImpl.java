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
import com.fun.business.sharon.common.Const;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
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

    @Value("${sharon.uploadPath}")
    private String uploadPath;

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

    @Override
    public Integer uploadFile(MultipartFile[] file, String description) {
        Integer result = 0;
//        try {
//            // 未定义上传路径，上传至默认目录
//            if (ObjectUtil.isNotEmpty(file)) {
//                for (MultipartFile sonFile : file) {
//                    String fileType = "";
//                    String originalName = sonFile.getOriginalFilename();
////                    String fileName = sonFile.getName();
//                    if (StringUtil.isNotEmpty(originalName)) {
//                        String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
//                        if ("doc".equals(prefix) || "docx".equals(prefix)) {
//                            fileType = Const.DOC_FILE;
//                        } else if ("xls".equals(prefix) || "xlsx".equals(prefix)) {
//                            fileType = Const.EXCEL_FILE;
//                        } else {
//                            fileType = Const.PIC_FILE;
//                        }
//                    }
//                    String uniquenessName = new Date().getTime() + originalName;
//                    String filePath = uploadPath + fileType + uniquenessName;
//                    File newFile = new File(filePath);
//                    sonFile.transferTo(newFile);
//
//                    // 存入表信息
//                    FileInfo pictureInfo = new FileInfo();
//                    pictureInfo.setFileName(sonFile.getOriginalFilename());
//                    pictureInfo.setUrl(Const.DOMAIN + "/" + uniquenessName);
//                    pictureInfo.setFileIntroduce(description);
//
//                    long size = sonFile.getSize();
//                    DecimalFormat df = new DecimalFormat("#.00");
//                    String fileSizeString = "";
//                    if (size < 1024) {
//                        fileSizeString = df.format((double) size) + "B";
//                    } else if (size < 1048576) {
//                        fileSizeString = df.format((double) size / 1024) + "K";
//                    } else if (size < 1073741824) {
//                        fileSizeString = df.format((double) size / 1048576) + "M";
//                    } else {
//                        fileSizeString = df.format((double) size / 1073741824) +"G";
//                    }
//                    pictureInfo.setSize(fileSizeString);
//                    pictureInfo.setCreateAt(new Date());
//                    pictureInfo.setUpdateAt(new Date());
//                    pictureInfo.setPath(filePath);
//                    result = fileInfoMapper.insert(pictureInfo);
//                }
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage(),e);
//        }
        return result;
    }
}
