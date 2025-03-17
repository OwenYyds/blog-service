package com.owen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    	private Integer id;
		private String name;
		private String createTime;
		private String updateTime;
		private Integer deleted;
}
