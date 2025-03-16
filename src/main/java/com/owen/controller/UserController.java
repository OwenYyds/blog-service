package com.owen.controller;

import com.owen.pojo.ResponseMessage;
import com.owen.pojo.User;
import com.owen.service.UserService;
import com.owen.utills.PasswordEncodeUtils;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseMessage<List<User>> findAll() {
		return ResponseMessage.success(userService.findAll());
	}

	@PostMapping("/login")
	public ResponseMessage<User> login(@Pattern(regexp = "^\\S{5,16}$") String userName, @Pattern(regexp = "^\\S{5,16}$") String password) {
		// check if user exists
		User user = userService.findByUserName(userName);
		if (user == null ) {
			return ResponseMessage.error(500,"User does not exist");
		}else {
			// check password
			if (user.getPassword().equals(PasswordEncodeUtils.encodePassword(password))) {
				return ResponseMessage.success(user);
			}else {
				return ResponseMessage.error(500,"username or password is incorrect");
			}
		}
	}

	@PostMapping("/register")
	public ResponseMessage<User> register(@Pattern(regexp = "^\\S{5,16}$") String userName, @Pattern(regexp = "^\\S{5,16}$") String password) {
		// check if user exists
		User user = userService.findByUserName(userName);
		if (user != null ) {
			return ResponseMessage.error(500,"User already exists");
		}else {
			// create user
			User newUser = new User();
			newUser.setUserName(userName);
			newUser.setPassword(password);
			// save user
			userService.add(newUser);

			return ResponseMessage.success(newUser);
		}

	}
}
