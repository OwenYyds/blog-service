package com.owen.config;

import com.owen.pojo.DigitalOceanProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3ClientConfig {
	private final DigitalOceanProperties properties;

	public S3ClientConfig(DigitalOceanProperties properties) {
		this.properties = properties;
	}

	@Bean
	public S3Client s3Client() {
		return S3Client.builder()
		               .credentialsProvider(StaticCredentialsProvider.create(
				               AwsBasicCredentials.create(properties.getAccessKey(), properties.getSecretKey())))
		               .endpointOverride(URI.create(properties.getEndpoint()))
		               .region(Region.of(properties.getRegion()))
		               .build();
	}
}
