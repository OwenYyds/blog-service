package com.owen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
	private Integer id;
	private String name;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer deleted;
}
