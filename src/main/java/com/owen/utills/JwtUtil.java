package com.owen.utills;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration-time}")
	private Integer expirationTime;

	private Algorithm algorithm;

	@PostConstruct
	public void init() {
		algorithm = Algorithm.HMAC256(secret);
	}

	public String generateToken(Map<String, Object> claims) {
		return JWT.create()
		          .withClaim("claims", claims)
		          .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
		          .sign(algorithm);
	}

	public Map<String, Object> verifyToken(String token) {
		return JWT.require(algorithm)
		          .build()
		          .verify(token)
		          .getClaim("claims")
		          .asMap();
	}
}