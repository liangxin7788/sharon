package com.fun.business.sharon.biz.business.bean.poi;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.fun.business.sharon.common.GlobalResult;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.fun.business.sharon.biz.business.bean.poi
 * @ClassName: WebDownloadTestController
 * @Description: 测试Excel下载
 * @Author: liangxin
 * @CreateDate: 2019/7/11 16:20
 * @UpdateDate: 2019/7/11 16:20
 */
@RestController
@RequestMapping("/web")
public class WebDownloadTestController {

    @GetMapping("/exportExcel")
//    @ApiOperation("测试7行代码下载Excel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
        String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .getBytes(), "UTF-8");
        Sheet sheet1 = new Sheet(1, 0);
        sheet1.setSheetName("第一个sheet");
        sheet1.setHead(creatHead());
        writer.write1(getListString(), sheet1);

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");

        writer.finish();
        if(outputStream != null){
            outputStream.flush();
        }
    }

    private List<List<String>> creatHead() {
        List<List<String>> list = new ArrayList<>();
        List<String> cloumn1= new ArrayList<>();
        List<String> cloumn2= new ArrayList<>();
        List<String> cloumn3= new ArrayList<>();
        List<String> cloumn4= new ArrayList<>();
        List<String> cloumn5= new ArrayList<>();
        cloumn1.add("姓名");
        cloumn2.add("年龄");
        cloumn3.add("地址");
        cloumn4.add("行业");
        cloumn5.add("喜好");
        list.add(cloumn1);
        list.add(cloumn2);
        list.add(cloumn3);
        list.add(cloumn4);
        list.add(cloumn5);
        return list;
    }

    private List<List<Object>> getListString() {
        List<List<Object>> rows = new ArrayList<>();
        // 写入数据
        for(int i = 0; i < 100; i++){
            List<Object> row = new ArrayList<>();
            row.add("名字" + i);
            row.add(10 + i);
            row.add("白庙");
            row.add("IT");
            row.add("like");
            rows.add(row);
        }
        return rows;
    }
}
