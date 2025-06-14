package com.owen.interceptors;

import com.owen.service.data.RedisService;
import com.owen.utills.JwtUtil;
import com.owen.utills.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RedisService redisService;

	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String token = request.getHeader("Authorization");

		try {
			if (token.startsWith("Bearer ")) {
				token = token.substring(7);
			}

			Map<String, Object> claims = jwtUtil.verifyToken(token);
			ThreadLocalUtil.set(claims);

			String redisToken = (String) redisService.getData(token);
			if (redisToken == null) {
				throw new RuntimeException();
			}

			return true;
		} catch (Exception e) {
			response.setStatus(401);
			return false;
		}

	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		ThreadLocalUtil.remove();
	}
}
