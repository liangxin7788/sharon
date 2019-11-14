package com.fun.business.sharon.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fun.business.sharon.common.Const;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisUtil {

	private static StringRedisTemplate stringRedisTemplate;

	@Autowired
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		RedisUtil.stringRedisTemplate = stringRedisTemplate;
	}

	public static String getValueFromRedis(String key) {
		return StringUtil.isEmpty(key) ? null : getValueFromRedis(key, "");
	}

	public static String getValueFromRedis(String key, String defaultValue) {
		try {
			String redisValue = stringRedisTemplate.opsForValue().get(key);
			return redisValue == null ? defaultValue : redisValue;
		} catch (Exception e) {
			log.debug("get value from redis error, key : " + key + " , defautValue : " + defaultValue, e);
			return defaultValue;
		}
	}

	/**
	 * 将字符串加入缓存，默认保存30分钟
	 * @param key
	 * @param value
	 */
	public static void setValueToRedis(String key, String value){
		try {
			if (!StringUtil.isEmpty(value) && !StringUtil.isEmpty(key)) {
				stringRedisTemplate.opsForValue().set(key, value, Const.REDIS_KEEP_MINUTE, TimeUnit.MINUTES);
			}
		} catch (Exception e) {
			log.debug("set value to redis error, key : " + key + ", value : " + value, e);
		}
	}

	/**
	 * 将对象的json格式放进缓存中
	 * @param key
	 * @param value
	 */
	public static void setValueToRedis(String key, Object value) {
		try {
			if (!ObjectUtil.isEmpty(value) && !StringUtil.isEmpty(key)) {
				stringRedisTemplate.opsForValue().set(key, ObjectUtil.toJson(value), Const.REDIS_KEEP_MINUTE,
						TimeUnit.MINUTES);
			}
		} catch (Exception e) {
			log.debug("set value to redis error, key : " + key + ", value : " + value, e);
		}
	}

	/**
	 * 将数据存入redis中， 且设置缓存时间
	 */
	public static void setValueToRedis(String key, Object value, int time, TimeUnit unit) {
		try {
			if (!ObjectUtil.isEmpty(value) && !StringUtil.isEmpty(key)) {
				stringRedisTemplate.opsForValue().set(key, ObjectUtil.toJson(value), time, unit);
			}
		} catch (Exception e) {
			log.debug("set value to redis error, key : " + key + ", value : " + value, e);
		}
	}

	/**
	 * 清除指定的key对应的redis中数据
	 */
	public static void clearRedis(String key) {
		try {
			if (!StringUtil.isEmpty(key)) {
				stringRedisTemplate.delete(key);
			}
		} catch (Exception e) {
			log.debug("delete data in redis error, key : " + key, e);
		}
	}

	/**
	 * 模糊清除redis数据
	 */
	public static void batchClearRedis(String key) {
		try {
			if (!StringUtil.isEmpty(key)) {
				stringRedisTemplate.delete(stringRedisTemplate.keys(key + "*"));
			}
		} catch (Exception e) {
			log.debug("delete data in redis error, key : " + key, e);
		}
	}


}
