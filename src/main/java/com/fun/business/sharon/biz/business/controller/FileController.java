package com.fun.business.sharon.biz.business.controller;


import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.service.FileInfoService;
import com.fun.business.sharon.common.Const;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.FileUtil;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileInfoService fileInfoService;

    @Value("${sharon.uploadPath}")
    private String uploadPath;

    @PostMapping("/uploadFile")
    @ApiOperation("文件上传至指定路径")
    public GlobalResult<?> uploadFile(MultipartFile[] file, @ApiParam("文件描述信息") @RequestParam(value = "description", required = false) String description) {
        try {
            // 未定义上传路径，上传至默认目录
            if (ObjectUtil.isNotEmpty(file)) {
                for (MultipartFile sonFile : file) {
                    String fileType = "";
                    String originalName = sonFile.getOriginalFilename();
//                    String fileName = sonFile.getName();
                    if (StringUtil.isNotEmpty(originalName)) {
                        String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                        if ("doc".equals(prefix) || "docx".equals(prefix)) {
                            fileType = Const.DOC_FILE;
                        } else if ("xls".equals(prefix) || "xlsx".equals(prefix)) {
                            fileType = Const.EXCEL_FILE;
                        } else {
                            fileType = Const.PIC_FILE;
                        }
                    }
                    String uniquenessName = new Date().getTime() + originalName;
                    String filePath = uploadPath + fileType + uniquenessName;
                    File newFile = new File(filePath);
                    sonFile.transferTo(newFile);

                    // 存入表信息
                    FileInfo pictureInfo = new FileInfo();
                    pictureInfo.setFileName(sonFile.getOriginalFilename());
                    pictureInfo.setUrl(Const.DOMAIN + "/" + uniquenessName);
                    pictureInfo.setFileIntroduce(description);
                    pictureInfo.setSize(sonFile.getSize());
                    pictureInfo.setCreateAt(new Date());
                    pictureInfo.setUpdateAt(new Date());
                    pictureInfo.setPath(filePath);
                    fileInfoService.save(pictureInfo);
                }
            }
        } catch (IOException e) {
            return GlobalResult.newError("上传失败");
        }
        return GlobalResult.newSuccess("上传成功");
    }

    @GetMapping("/downloadFile")
    @ApiOperation("下载文件")
    public ResponseEntity<InputStreamResource> downloadFile(HttpServletResponse response, Integer fileId) {
        FileInfo fileInfo = fileInfoService.getById(fileId);
        String url = fileInfo.getPath();
//        String url = fileInfo.getUrl();
        if (StringUtil.isNotEmpty(url)) {
            try {
                FileSystemResource file = new FileSystemResource(url);
                response.resetBuffer();
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add(
                        "Content-Disposition",
                        String.format("attachment; filename="
                                + new String((fileInfo.getFileName())
                                .getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentLength(file.contentLength())
                        .contentType(
                                MediaType
                                        .parseMediaType("application/octet-stream"))
                        .body(new InputStreamResource(file.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
