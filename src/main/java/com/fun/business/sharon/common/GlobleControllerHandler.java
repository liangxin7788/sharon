package com.fun.business.sharon.common;

import com.baomidou.mybatisplus.extension.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.fun.business.sharon.common
 * @ClassName: GlobleControllerHandler
 * @Description: 全局 controller 异常捕获，controller 层不写try catch了，业务层有try catch的话，要往外throw，这里才能接收到
 * @Author: liangxin
 * @CreateDate: 2019/10/28 17:30
 * @UpdateDate: 2019/10/28 17:30
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobleControllerHandler {

    @ExceptionHandler(value = Exception.class)
    public GlobalResult<?> handleException(HttpServletRequest request, Exception e){
        log.error(e.getMessage(),e);
        if(e instanceof MyBatisSystemException){
            if (e.getCause() instanceof PersistenceException) {
                if (e.getCause().getCause() instanceof OperateException) {
                    return GlobalResult.newError(e.getCause().getCause().getMessage());
                }
            }
        }else if (e instanceof OperateException){
            return GlobalResult.newError(e.getMessage());
        }else{
            return GlobalResult.newError("全局异常捕获生效！请查看 error 日志");
        }
        return GlobalResult.newError(e.getMessage());
    }

}
