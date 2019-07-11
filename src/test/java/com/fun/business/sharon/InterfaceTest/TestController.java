//package com.fun.business.sharon.InterfaceTest;
//
//import com.fun.business.sharon.SharonApplication;
//import com.fun.business.sharon.common.GlobalResult;
//import com.fun.business.sharon.utils.ExcelPoiUtil;
//import com.fun.business.sharon.utils.ObjectUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * @Package: com.fun.business.sharon.InterfaceTest
// * @ClassName: TestController
// * @Description: 接口测试专用
// * @Author: liangxin
// * @CreateDate: 2019/6/14 14:08
// * @UpdateDate: 2019/6/14 14:08
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SharonApplication.class)
//public class TestController {
//
//    @Test
//    public void testExcel() throws IOException {
//        List<String[]> excel = ExcelPoiUtil.readExcelInProject("ceshi.xlsx");
//        if (ObjectUtil.isNotEmpty(excel)) {
//            for (String[] obj : excel) {
//                String mainSku = obj[0];
//                String title = obj[1];
//                String description = obj[2];
//                System.out.println("mainSku:" + mainSku);
//                System.out.println("title:" + title);
//                System.out.println("description:" + description);
//            }
//        }
//    }
//
//}
