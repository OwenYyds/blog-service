package com.owen;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class JwtTest {

	@Test
	public void create (){
		Map<String,Object> claims = new HashMap<>();
		claims.put("username","owen");
		claims.put("password","owen");
		String token = JWT.create()
		                 .withClaim("user", claims)
		                 .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
		                 .sign(Algorithm.HMAC256("owen"));
		System.out.println(token);
	}

	@Test
	public void verify(){
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InBhc3N3b3JkIjoib3dlbiIsInVzZXJuYW1lIjoib3dlbiJ9LCJleHAiOjE3NDIxNDAyNzB9.f0U8UARv1pTaHkGFr9QxCrDLsC7xR9lZX1Ew7dR1_9w";
		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("owen"))
		                     .build()
		                     .verify(token);
		Map<String, Claim> claims = decodedJWT.getClaims();


		System.out.println(claims.get("user").asMap());
	}

 }