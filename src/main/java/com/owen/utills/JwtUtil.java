package com.owen.utills;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
	public static final String SECRET = "owen";
	public static final String EXPIRATION_TIME = "1000 * 60 * 60 * 12";

	public static String generateToken(Map<String, Object> claims) {
		return JWT.create()
				.withClaim("claims", claims)
				.withExpiresAt(Instant.parse(new Date(System.currentTimeMillis()) + EXPIRATION_TIME))
				.sign(Algorithm.HMAC256(SECRET));
	}

	public static Map<String, Object> verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
		        .build()
		        .verify(token)
		        .getClaim("claims")
		        .asMap();
	}

}
