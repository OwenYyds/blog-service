package com.owen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
public class Tag {
	private Integer id;
	private String name;
	private String createTime;
	private String updateTime;
	private Integer deleted;
}
