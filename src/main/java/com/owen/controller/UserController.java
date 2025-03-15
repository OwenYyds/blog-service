package com.owen.controller;

import com.owen.pojo.ResponseMessage;
import com.owen.pojo.User;
import com.owen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseMessage<List<User>> findAll() {
		return ResponseMessage.success(userService.findAll());
	}

	@PostMapping("/register")
	public ResponseMessage<User> register(String userName, String password) {
		if(userName == null || password == null) {
			return ResponseMessage.error(500,"Username or password cannot be empty");
		}
		// check if user exists
		User user = userService.findByUserName(userName);
		if (user != null ) {
			return ResponseMessage.error(501,"User already exists");
		}else {
			// create user
			User newUser = new User();
			newUser.setUserName(userName);
			newUser.setPassword(password);
			newUser.setRole("user");
			// save user
			userService.add(newUser);

			return ResponseMessage.success(newUser);
		}

	}
}
