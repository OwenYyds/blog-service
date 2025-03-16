package com.owen.service;

import com.owen.pojo.User;

import java.util.List;


public interface UserService {
	User findByUserName(String userName);

	void add(User user);

	List<User> findAll();

	void update(User user);

	void updateAvatar(String avatarUrl);

	void updatePassword(String newPassword);
}
