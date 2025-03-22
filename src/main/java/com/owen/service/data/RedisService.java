package com.owen.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Value("${jwt.expiration-time}")
	private long TIME_OUT;

	public void saveData(String key, Object value) {
		redisTemplate.opsForValue().set(key, value, TIME_OUT, TimeUnit.MILLISECONDS);
	}

	public Object getData(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void deleteData(String key) {
		redisTemplate.delete(key);
	}
}