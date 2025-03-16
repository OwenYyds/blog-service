package com.owen.service.impl;

import com.owen.mapper.UserMapper;
import com.owen.pojo.User;
import com.owen.service.UserService;
import com.owen.utills.PasswordEncodeUtil;
import com.owen.utills.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
		user.setPassword(PasswordEncodeUtil.encodePassword(user.getPassword()));
        userMapper.add(user);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public void updateAvatar(String avatarUrl) {
		Map<String, Object>  map = ThreadLocalUtil.get();
		Integer id = (Integer) map.get("id");
		userMapper.updateAvatar(avatarUrl  , id);
	}

	@Override
	public void updatePassword(String newPassword) {
		Map<String, Object>  map = ThreadLocalUtil.get();
		Integer id = (Integer) map.get("id");

		userMapper.updatePassword(PasswordEncodeUtil.encodePassword(newPassword), id);
	}
}
