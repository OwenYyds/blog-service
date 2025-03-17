package com.owen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private Integer id;
	private Integer userId;
	private String title;
	private String content;
	private String summary;
	private String coverImage;
	private Integer categoryId;
	private Integer status;
	private Integer viewCount;
	private Integer likeCount;
	private Integer commentCount;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer deleted;
}