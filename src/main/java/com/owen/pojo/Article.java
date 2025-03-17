package com.owen.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;


@Data
public class Article {
	private Integer id;
	private Integer userId;
	@NotEmpty
	@Pattern(regexp = "^\\S{2,10}$")
	private String title;
	@NotEmpty
	private String content;
	private String summary;
	@URL
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