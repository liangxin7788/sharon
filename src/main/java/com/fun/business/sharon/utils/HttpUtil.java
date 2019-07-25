package com.fun.business.sharon.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * http 工具类
 */
public class HttpUtil {

    public static void main(String[] args) throws Exception {
//        String post = post("http://192.168.160.249/wms/foreigns/queryWarehouseIdBySkus", null, "[\"15579-1\",\"D0482-6\",\"14630-5\",\"D1616\"]");
        String post = HttpUtil.post("http://192.168.160.249/wms/foreigns/queryWarehouseIdBySkus", null,
                JSONObject.toJSONString(FileUtils.readLines(new File("C:\\Users\\Administrator\\Desktop\\11.txt"), "utf-8")));

        List<Object> list = JSON.parseArray("[" + post + "]");
        String message = "";
        for (Object object : list) {
            Map<String, Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
            message = ret.get("message").toString();
        }

//        System.out.println(message);
        List<Object> messageList = JSON.parseArray(message);

        File file = new File("C:" + File.separator + "demo" + File.separator + "sku_bak.txt");

        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        Writer out = new FileWriter(file);

        int count = 0;
        for (Object object : messageList) {
            Map<String, Object> ret = (Map<String, Object>) object;

            String sku = ret.get("sku").toString();
            String warehouse = ret.get("warehouseId").toString();

            if (StringUtils.isNotEmpty(warehouse)) {
                warehouse = warehouse.equals("1") ? "0" : "1";
//              out.write("UPDATE stockkeepingunit set isxckrecord = '" + warehouse + "' WHERE article_number = '" + sku + "';" +"\n");
                out.write(sku + "\n");
//              String sql = "select isxckrecord, " + warehouse + " from stockkeepingunit where article_number = '" + sku + "';\n";
//              out.write(sql);
            }
        }
        out.close();
    }

    public static String post(String requestUrl, String accessToken, String params)
            throws Exception {
        String contentType = "application/json";
//        String contentType = "application/x-www-form-urlencoded";
        return HttpUtil.post(requestUrl, accessToken, contentType, params);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params)
            throws Exception {
        String encoding = "UTF-8";
        if (requestUrl.contains("nlp")) {
            encoding = "GBK";
        }
        return HttpUtil.post(requestUrl, accessToken, contentType, params, encoding);
    }

    public static String post(String requestUrl, String accessToken, String contentType, String params, String encoding)
            throws Exception {
        String url = requestUrl;
        if (StringUtil.isNotEmpty(accessToken)) {
            url = requestUrl + "?access_token=" + accessToken;
        }
        return HttpUtil.postGeneralUrl(url, contentType, params, encoding);
    }

    public static String postGeneralUrl(String generalUrl, String contentType, String params, String encoding)
            throws Exception {
        URL url = new URL(generalUrl);
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // 设置通用的请求属性
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(params.getBytes(encoding));
        out.flush();
        out.close();

        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> headers = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : headers.keySet()) {
            System.err.println(key + "--->" + headers.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), encoding));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        System.err.println("result:" + result);
        return result;
    }
}
