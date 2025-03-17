package com.owen.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@NonNull
	private Integer id;
	private String userName;
	@JsonIgnore
	private String password;
	@NonNull
	@Email
	private String email;
	private String phone;
	private String avatar;
	private    createTime;
	private String updateTime;
	private String lastLoginTime;
	private Integer status;
	private Integer deleted;
	private String role;
	private String token;
	private String description;
	@NotEmpty
	@Pattern(regexp = "^\\S{2,10}$")
	private String nickName;
}
