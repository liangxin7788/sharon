package com.fun.business.sharon.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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

    // ==============================以下是原生HTTP请求玩法======================================

    private static final String HTTP = "http";

    private static final String HTTPS = "https";

    private static final int MAX_CONNECTIONS = 200;

    private static final int MAX_PER_ROUTE = 100;

    private static RestTemplate httpRestTemplate;

    private static RestTemplate httpsRestTemplate;

    static {

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(60000);
        requestFactory.setConnectTimeout(60000);    // 链接超时时间60s
        httpRestTemplate = new RestTemplate(requestFactory);
//        try {
//            // 全部信任 不做身份鉴定
//            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//                @Override
//                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//                    return true;
//                }
//            }).build();
//            // socket factory
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
//                    new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
//            // registry
//            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
//                    .register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
//            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
//            cm.setMaxTotal(MAX_CONNECTIONS);
//            cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
//            // http client
//            CloseableHttpClient httpsClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm)
//                    .setConnectionManagerShared(true).build();
//            // 获取https的restTemplate
//            httpsRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpsClient));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 发送请求
     *
     * @param <T, R>
     *
     * @param httpParams 请求参数
     * @param resultType 返回值类型
     *
     * @return
     */
    public static <T, R> R exchange(HttpParams<T> httpParams, Class<R> resultType) {
        if (!checkHttpParams(httpParams) || resultType == null) {
            return null;
        }

        HttpEntity<T> httpEntity = new HttpEntity<>(httpParams.getBody(), httpParams.getHttpHeaders());
        // 执行HTTP请求
        ResponseEntity<R> response = getRestTemplate(httpParams.getUrl()).exchange(httpParams.getUrl(),
                httpParams.getHttpMethod(), httpEntity, resultType);

        return response.getBody();
    }

    /**
     * form表单请求
     *
     * @param url 请求地址
     * @param method 请求方式
     * @param params body参数
     * @return
     */
    public static String form(String url, HttpMethod method, MultiValueMap<String, String> params) {
        HttpParams<MultiValueMap<String, String>> httpParams = new HttpParams<>(url, method);
        httpParams.setMediaType(MediaType.APPLICATION_FORM_URLENCODED);
        httpParams.setBody(params);
        return exchange(httpParams, String.class);
    }

    private static boolean checkHttpParams(HttpParams<?> httpParams) {
        return !(httpParams == null || org.springframework.util.StringUtils.isEmpty(httpParams.getUrl()));
    }

    /**
     * 根据请求获取http / https 的RestTemplate
     *
     * @param url
     * @return
     */
    public static RestTemplate getRestTemplate(String url) {
//        return url.toLowerCase().startsWith(HTTPS) ? httpsRestTemplate : httpRestTemplate;
        return httpRestTemplate;
    }

    /**
     * 下载文件字节码
     * @param fileUrl
     * @return
     */
    public static byte[] downloadBytes(String fileUrl) {
        if (org.springframework.util.StringUtils.isEmpty(fileUrl)) {
            return null;
        }

        return exchange(new HttpParams<>(fileUrl, HttpMethod.GET), byte[].class);
    }

    /**
     *
     * @Description: URL解码
     *
     * @param value 字符串
     * @return
     * @Author: Kevin
     * @Date: 2018/12/07
     * @Version: 0.0.1
     */
    public static String decode(String value) {
        if (org.springframework.util.StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return URLDecoder.decode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     *
     * @Description: URL编码
     *
     * @param value
     * @return
     * @Author: Kevin
     * @Date: 2018/12/07
     * @Version: 0.0.1
     */
    public static String encode(String value) {
        if (org.springframework.util.StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return URLEncoder.encode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ============================= 使用样例 =====================================
    /*public static List<TransferWmsInfoVo> getInfoFromWms(Integer isXckCode, List<String> skus){
        List<TransferWmsInfoVo> message = null;
        try {
            HttpParams<Object> objectHttpParams = null;
            if (isXckCode == 1){
                objectHttpParams = new HttpParams<>(Const.TEST_NEW_URL + "wms/foreigns/queryProductSkuAndQcCategoryList", HttpMethod.POST);

            }else {
                objectHttpParams = new HttpParams<>(Const.TEST_OLD_URL + "wms/foreigns/queryProductSkuAndQcCategoryList", HttpMethod.POST);
            }
            objectHttpParams.setBody(skus);
            // 返回值用map接收的
            Map infoFromWms = HttpUtils.exchange(objectHttpParams, Map.class);
            if (infoFromWms != null && !"[]".equals(infoFromWms.get("message"))) {
                message = JSON.parseArray((String) infoFromWms.get("message"), TransferWmsInfoVo.class) ;
            }
        } catch (Exception e) {
            log.error("同步数据时，调用仓库获取信息失败！" + e.getMessage(), e);
        }
        return message;
    }

    // List<JSONObject> jsonObjectList = JSON.parseObject(json, List.class);

    */

}
