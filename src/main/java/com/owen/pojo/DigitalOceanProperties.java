package com.owen.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "digital-ocean")
@Data
public class DigitalOceanProperties {
	private String spaceName;
	private String region;
	private String accessKey;
	private String secretKey;
	private String endpoint;
	private String cdnEndpoint;
}
