package com.fun.business.sharon.utils;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun.business.sharon.common.OperateException;

public class ObjectUtil {
	
	public static void assertNull(Object obj, String message) throws Exception {
		if (isEmpty(obj)) {
			throw new OperateException(String.format("NULL Exception,message:%s 为空", message));
		}
	}
	
	/**
	 * 判断对象或对象数组中每一个对象是否为空 对象为null，字符序列长度为0，集合类、Map为empty
	 *
	 * @param obj
	 *            对象
	 * @return true or false
	 */
	public static boolean isEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj != null) {
			if (obj instanceof String) { // 字符串
				isEmpty = StringUtil.isEmpty(String.valueOf(obj));
			} else if (obj instanceof Map) { // map
				isEmpty = ((Map<?, ?>) obj).isEmpty();
			} else if (obj instanceof Collection) { // 集合
				isEmpty = ((Collection<?>) obj).isEmpty();
			} else if (obj instanceof Number) {// 数值
				isEmpty = obj.hashCode() == 0;
			} else if (obj.getClass().isArray()) {// 数组
				isEmpty = Array.getLength(obj) == 0;
			}
		} else {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * 对象转json
	 * @param obj
	 * @return
	 * @throws OperateException
	 */
	public static String toJson(Object obj) throws OperateException {
		if (obj instanceof String) {
			return obj.toString();
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new OperateException("Json 转换出错");
		}
	}
	
	public static <T> T toObject(String str, Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(str, clazz);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> List<?> writeWithView(List<T> t, Class<?> clazz) throws OperateException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			objectMapper.writerWithView(clazz).writeValue(bos, t);
			return toObject(bos.toString(), List.class);
		} catch (Exception e) {
			throw new OperateException("Json视图转换出错,message:" + e);
		}
	}
	
	
}
