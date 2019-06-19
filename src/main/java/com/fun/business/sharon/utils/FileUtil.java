package com.fun.business.sharon.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Package: com.fun.business.sharon.utils
 * @ClassName: FileUtil
 * @Description: 文件操作工具类
 * @Author: liangxin
 * @CreateDate: 2019/6/18 18:45
 * @UpdateDate: 2019/6/18 18:45
 */
public class FileUtil {

    /**
     * 上传文件至指定路径
     * @param picFile
     * @param targetPath
     * @throws IOException
     */
    public static void UploadFile(MultipartFile[] picFile, String targetPath) throws IOException {
        if (ObjectUtil.isNotEmpty(picFile)) {
            for (MultipartFile file : picFile) {
                String filename = file.getOriginalFilename();
                String filePath = targetPath + "/" + new Date().getTime() + filename;
                File newFile = new File(filePath);
                file.transferTo(newFile);
            }
        }
    }

}
