package com.owen.service;

import com.owen.pojo.User;

import java.util.List;


public interface UserService {
	User findByUserName(String userName);

	void add(User user);

	List<User> findAll();
}
