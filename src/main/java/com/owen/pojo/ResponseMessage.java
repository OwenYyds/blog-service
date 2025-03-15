package com.owen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {

	private Integer code;
	private String message;
	private T data;

	public static <T> ResponseMessage<T> success() {
		return new ResponseMessage<>(HttpStatus.OK.value(), "Success", null);
	}

	public static <T> ResponseMessage<T> success(T data) {
		return new ResponseMessage<>(HttpStatus.OK.value(), "Success", data);
	}

	public static <T> ResponseMessage<T> error(Integer code, String message) {
		return new ResponseMessage<>(code, message, null);
	}
}
