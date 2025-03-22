package com.owen.utills;

import com.owen.pojo.DigitalOceanProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUtil {

	private final S3Client s3Client;
	private final DigitalOceanProperties properties;

	public String uploadFile(MultipartFile file, String folder) {
		if (file.isEmpty()) {
			throw new IllegalArgumentException("File is empty");
		}

		String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		String suffix = filename.contains(".") ? filename.substring(filename.lastIndexOf(".")) : "";
		String fileNameWithUUIDAndFolder = folder + "/" + UUID.randomUUID() + suffix;

		try {
			PutObjectRequest request = PutObjectRequest.builder()
			                                           .bucket(properties.getSpaceName())
			                                           .key(fileNameWithUUIDAndFolder)
			                                           .build();

			s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

			return properties.getCdnEndpoint() + fileNameWithUUIDAndFolder;
		} catch (IOException e) {
			log.error("Failed to upload file: {}", filename, e);
			throw new RuntimeException("File upload failed", e);
		}
	}
}
