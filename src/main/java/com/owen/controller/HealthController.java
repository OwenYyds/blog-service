package com.owen.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	@Operation(summary = "Health Check", description = "Returns OK if the service is running.")
	@GetMapping("/health")
	public String healthCheck() {
		return "OK";
	}
}

