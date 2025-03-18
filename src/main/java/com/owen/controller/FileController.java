package com.owen.controller;

import com.owen.pojo.ResponseMessage;
import com.owen.utills.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

	private final FileUtil fileUtil;

	@PostMapping("/upload")
	public ResponseMessage<String> upload(MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseMessage.error(400, "File is empty");
		}

		String fileUrl = fileUtil.uploadFile(file, "user-avatar");

		if (fileUrl == null) {
			return ResponseMessage.error(500, "File upload failed");
		}

		return ResponseMessage.success(fileUrl);

		// TD: parse to base64 and return
	}
}
