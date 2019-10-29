package com.fun.business.sharon.biz.business.controller;

import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.service.FileInfoService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.HttpUtil;
import com.fun.business.sharon.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

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
@Api(description = "文件上传下载")
public class FileController {

    @Autowired
    private FileInfoService fileInfoService;

    @ApiOperation("文件上传")
    @PostMapping(value = "/uploadFile")
    public GlobalResult<?> uploadFile(@RequestParam(value = "file", required = true)MultipartFile[] file, @ApiParam("文件描述信息") @RequestParam(value = "description", required = false) String description) {
        Integer result = fileInfoService.uploadFile(file, description);
        return GlobalResult.newSuccess(result);
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

    @GetMapping("/fileDownload")
    @ApiOperation("/文件下载接口")
    public void fileDownload(final HttpServletResponse response, String fileName, Integer fileId) throws Exception{

        FileInfo fileInfo = fileInfoService.getById(fileId);
        String url = fileInfo.getPath();
        if (StringUtils.isNotEmpty(url)) {
            byte[] data = HttpUtil.downloadBytes(url);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileInfo.getFileName().getBytes("utf-8")) + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
            response.flushBuffer();
            System.out.println("文件 " + fileName + " 下载完成");
        }
    }

}
