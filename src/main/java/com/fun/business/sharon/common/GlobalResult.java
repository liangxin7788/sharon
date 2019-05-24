package com.fun.business.sharon.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fun.business.sharon.utils.StringUtil;

import lombok.Data;

/**
 * 全局返回格式定义
 * @author liangxin
 *
 */
@Data
public class GlobalResult<T> implements Serializable  {

    private static final long serialVersionUID = 1271253060972891256L;

    /**
     * api返回状态
     */
    private boolean success = true;

    /**
     * api返回值
     */
    private T result;

    /**
     * 异常信息
     */
    private String errorMsg;

    public GlobalResult() {
        super();
    }

    /**
     * 创建成功的GlobalResult
     * 
     * @param <R>
     * 
     * @return globalResult
     */
    public static <R> GlobalResult<R> newSuccess() {
        return newSuccess(null);
    }

    /**
     * 创建错误的GlobalResult
     * 
     * @param <R>
     * 
     * @param errorMsg 错误信息
     * @return globalResult
     */
    public static <R> GlobalResult<R> newError(String errorMsg) {
    	GlobalResult<R> globalResult = new GlobalResult<>();
    	globalResult.setSuccess(false);
    	globalResult.setErrorMsg(errorMsg);

        return globalResult;
    }

    /**
     * 创建成功的GlobalResult
     * 
     * @param result 返回值
     * @return globalResult
     */
    public static <R> GlobalResult<R> newSuccess(R result) {
    	GlobalResult<R> globalResult = new GlobalResult<>();
    	globalResult.setResult(result);
        return globalResult;
    }

    /**
     * 生成apiResult
     * 
     * @param success
     * @param result
     * @param errorMsg
     * @return
     */
    public static <R> GlobalResult<R> of(boolean success, R result, String errorMsg) {
    	GlobalResult<R> globalResult = new GlobalResult<>();
    	globalResult.setSuccess(success);
    	globalResult.setResult(result);
    	globalResult.setErrorMsg(errorMsg);

        return globalResult;
    }

    /**
     * description: 转换为指定类型
     * @param typeRefer
     * @return
     */
    public <R> R getResultValue(TypeReference<R> typeRefer) {
        if (result == null || typeRefer == null) {
            return null;
        }
        if (result.getClass() == String.class) {
            return JSON.parseObject((String) result, typeRefer);
        }
        return null;
    }

    /**
     * 连接是否被拒绝，服务中断
     * 
     * @return
     */
    public boolean isConnectionRefused() {
        return StringUtil.isNotEmpty(errorMsg) && errorMsg.contains("Connection refused");
    }

}
