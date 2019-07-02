package com.fun.business.sharon.utils;

import com.fun.business.sharon.common.OperateException;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Package: com.fun.business.sharon.utils
 * @ClassName: CheckParamUtil
 * @Description: 反射判断参数是否为空
 *                  使用时，可在对象属性上加 @ApiModelProperty 注解，这样报错信息更准确（如：@ApiModelProperty("首选图片") 报出中文）
 * @Author: liangxin
 * @CreateDate: 2019/7/1 14:11
 * @UpdateDate: 2019/7/1 14:11
 */
public class CheckParamUtil {

    public static void checkParamForCommit(Object object, String[] checkPram) throws OperateException {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        if (declaredMethods != null) {
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod != null) {
                    String name = declaredMethod.getName();
                    if (name.startsWith("get")) {
                        try {
                            String substring = name.substring(3);
                            for (String s : checkPram) {
                                if (s.equalsIgnoreCase(substring)) {
                                    Object result = declaredMethod.invoke(object);
                                    if (result != null) {
                                        if (StringUtils.isEmpty(result.toString())) {
                                            checkParamDetail(object,s);
                                        }
                                    } else {
                                        checkParamDetail(object,s);
                                    }
                                }
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void checkParamDetail(Object object, String s) throws OperateException {
        Field declaredField = null;
        try {
            declaredField = object.getClass().getDeclaredField(s);
        } catch (NoSuchFieldException e) {
            throw new OperateException(s + "参数未填写完成，不能提交");
        }
        if(declaredField != null){
            ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
            if(annotation != null){
                throw new OperateException(annotation.value() + "参数未填写完成，不能提交");
            }else{
                throw new OperateException(s + "参数未填写完成，不能提交");
            }
        }else{
            throw new OperateException(s + "参数未填写完成，不能提交");
        }
    }


}
