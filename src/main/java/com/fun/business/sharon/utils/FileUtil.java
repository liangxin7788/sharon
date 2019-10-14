package com.fun.business.sharon.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    /**
     * 写出文件到本地某路径
     * @param content
     * @param targetPath
     * @throws IOException
     */
    public static void readFile(String content, String targetPath) throws IOException {
        File file = new File(targetPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //2：准备输出流
        Writer out = new FileWriter(file);
        out.write(content +"\n");
        out.close();
    }

    public static void charOutStream() throws Exception{
        // 1：利用File类找到要操作的对象
        File file = new File("C:" + File.separator + "demo" + File.separator + "test.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        //2：准备输出流
        Writer out = new FileWriter(file);
        String str = "测试字符流, 哈哈";
        for(int i = 0; i < 10; i++){
            out.write(str + i +"\n");
        }
        out.close();

    }

    public static void byteOutStream() throws Exception {
        //1:使用File类创建一个要操作的文件路径
        File file = new File("D:" + File.separator + "demo" + File.separator + "test.txt");
        if(!file.getParentFile().exists()){ //如果文件的目录不存在
            file.getParentFile().mkdirs(); //创建目录
        }

        //2: 实例化OutputString 对象
        OutputStream output = new FileOutputStream(file);

        //3: 准备好实现内容的输出
        String msg = "HelloWorld";
        //将字符串变为字节数组
        byte data[] = msg.getBytes();
        output.write(data);
        //4: 资源操作的最后必须关闭
        output.close();
    }

    public static void main(String[] args) throws Exception {
        //字节流
//        byteOutStream();

        //字符流 (输出流中含有中文时使用字符流)
        charOutStream();
        System.out.println("输出完成");

        String str = "ss";
        if (StringUtils.isEmpty(str)) {

        }

        if (StringUtils.isBlank(str)) {

        }

    }

}
