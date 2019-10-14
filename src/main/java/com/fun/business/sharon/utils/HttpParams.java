package com.fun.business.sharon.utils;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 * @Package: com.fun.business.sharon.utils
 * @ClassName: HttpParams
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/10/14 9:31
 * @UpdateDate: 2019/10/14 9:31
 */
@Data
public class HttpParams<T> {

        /**
         * url
         */
        private String url;

        /**
         * http请求方式， 默认GET
         */
        private HttpMethod httpMethod = HttpMethod.GET;

        /**
         * httpContentType， 默认application/json;charset=UTF-8
         */
        private MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;

        /**
         * http请求方式， 默认GET
         */
        private HttpHeaders httpHeaders;

        /**
         * http body参数
         */
        private T body;

    public HttpParams() {
        this.httpHeaders = new HttpHeaders();
    }

    public HttpParams(String url, HttpMethod httpMethod) {
        this();
        this.url = url;
        this.httpMethod = httpMethod;
    }

        public void addHeader(String headerName, String headerValue) {
        httpHeaders.add(headerName, headerValue);
    }

        public HttpHeaders getHttpHeaders() {
        httpHeaders.setContentType(mediaType);
        return httpHeaders;
    }

}
