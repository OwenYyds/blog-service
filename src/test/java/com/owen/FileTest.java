package com.owen;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Response;

import java.net.URI;
import java.nio.file.Paths;

public class FileTest {
	private static final String SPACE_NAME = "";
	private static final String REGION = "";
	private static final String ACCESS_KEY = "";
	private static final String SECRET_KEY = "";
	private static final String ENDPOINT = "";
	@Test
	public void testUploadFile() {
		S3Client s3 = S3Client.builder()
		                      .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
		                      .endpointOverride(URI.create(""))
		                      .region(Region.of(REGION))
		                      .build();

		PutObjectRequest request = PutObjectRequest.builder()
		                                           .bucket(SPACE_NAME)
		                                           .key("2.txt")
		                                           .build();

		s3.putObject(request, Paths.get("/Users/owen/Downloads/BetterTouchTool_5_155.dmg"));
		System.out.println("File uploaded successfully!");
	}
}
