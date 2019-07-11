package com.fun.business.sharon;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.fun.business.sharon.biz.business.bean.poi.ExcelModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTest {

	@Test
	public void test1(){
		
	}

	/**
	 * 简单Excel生成，通过！
	 * @throws Exception
	 */
	@Test
	public void writeExcel()throws Exception{
		// 文件输出位置
		FileOutputStream fileOutputStream = new FileOutputStream("C:\\demo\\test.xlsx");
		ExcelWriter writer = EasyExcelFactory.getWriter(fileOutputStream);
		// 写出只有一个sheet的Excel文件
		Sheet sheet = new Sheet(1, 0, ExcelModel.class);

		sheet.setSheetName("first sheet name");
		writer.write(createModelList(), sheet);

		// 输出
		writer.finish();
		// 关流
		if (fileOutputStream != null) {
			fileOutputStream.close();
		}
	}

	private List<? extends BaseRowModel> createModelList() {
		List<ExcelModel> writeModels = new ArrayList<>();
		// 写入数据
		for(int i = 0; i < 100; i++){
			ExcelModel excelModel = ExcelModel.builder().userName("test name" + i).address("address").age(10 + i).email("294051211@qq.com").build();
			writeModels.add(excelModel);
		}
		return writeModels;
	}
}
