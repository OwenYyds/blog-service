package com.owen.service.impl;

import com.owen.mapper.UserMapper;
import com.owen.pojo.User;
import com.owen.service.UserService;
import com.owen.utills.PasswordEncodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}

	@Override
	public void add(User user) {
		user.setPassword(PasswordEncodeUtils.encodePassword(user.getPassword()));
        userMapper.add(user);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
}
