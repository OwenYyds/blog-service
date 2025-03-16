package com.owen.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private String userName;
	@JsonIgnore
	private String password;
	private String email;
	private String phone;
	private String avatar;
	private String createTime;
	private String updateTime;
	private String lastLoginTime;
	private Integer status;
	private Integer deleted;
	private String role;
	private String token;
	private String description;
	private String nickName;
}
