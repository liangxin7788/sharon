package com.fun.business.sharon.utils;

public class StringUtil {

	/**
	 * 
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return
	 * @author Administrator
	 * @date 下午11:01:34
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
