package com.fun.business.sharon.commontest;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.fun.business.sharon.commontest
 * @ClassName: ListFileTest
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/7/25 14:56
 * @UpdateDate: 2019/7/25 14:56
 */
public class ListFileTest {

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("aa");
        lines.add("bb");
        lines.add("cc");
        lines.add("dd");
        lines.add("ee");
        lines.add("ff");
        lines.add("gg");

        // 会自动换行的
//        FileUtils.writeLines(new File("C:\\demo\\line.txt"), lines);

        List<String> re = FileUtils.readLines(new File("C:\\demo\\line.txt"), "utf-8");

        for (String s : re) {
            System.out.println(s);
        }

        System.out.println("文件写出完成！");

    }


}
