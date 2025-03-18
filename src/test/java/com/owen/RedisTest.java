package com.owen;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;

@SpringBootTest
public class RedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Test
	void test(){

		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
		stringStringValueOperations.set("name", "owen");

		System.out.println(stringStringValueOperations.get("name"));

	}
}
