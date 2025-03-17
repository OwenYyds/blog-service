package com.owen.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private Integer id;
	private Integer userId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private String summary;
	private String coverImage;
	private Integer categoryId;
	private Integer status;
	private Integer viewCount;
	private Integer likeCount;
	private Integer commentCount;
	private String createTime;
	private String updateTime;
	private Integer deleted;
}