package com.owen.controller;

import com.owen.pojo.ResponseMessage;
import com.owen.pojo.User;
import com.owen.service.UserService;
import com.owen.service.data.RedisService;
import com.owen.utills.JwtUtil;
import com.owen.utills.PasswordEncodeUtil;
import com.owen.utills.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RedisService redisService;

	@GetMapping
	public ResponseMessage<List<User>> findAll() {
		return ResponseMessage.success(userService.findAll());
	}

	@PostMapping("/login")
	public ResponseMessage<String> login(@Pattern(regexp = "^\\S{5,16}$") String userName, @Pattern(regexp = "^\\S{5,16}$") String password) {
		// check if user exists
		User user = userService.findByUserName(userName);
		if (user == null ) {
			return ResponseMessage.error(500,"User does not exist");
		}else {
			// check password
			if (user.getPassword().equals(PasswordEncodeUtil.encodePassword(password))) {
				Map<String, Object> claims = new HashMap<>();
				claims.put("id", user.getId());
				claims.put("username", user.getUserName());
				String token = jwtUtil.generateToken(claims);
				// save token to redis
				redisService.saveData(token, token);
				return ResponseMessage.success(token);
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

	@GetMapping("/info")
	public ResponseMessage<User> getUserByJwt() {

		Map<String, Object> map = ThreadLocalUtil.get();
		User user = userService.findByUserName(map.get("username").toString());

		return ResponseMessage.success(user);
	}


	@PutMapping("/update")
	public ResponseMessage<User> update(@RequestBody User user) {
		userService.update(user);
		return ResponseMessage.success(user);
	}

	@PatchMapping("/update-avatar")
	public ResponseMessage<String> updateAvatar(@RequestParam @URL String avatarUrl) {
		userService.updateAvatar(avatarUrl);
		return ResponseMessage.success();
	}

	@PatchMapping("/update-password")
	public ResponseMessage<String> updatePassword(@RequestBody Map<String, String> passwordMap, @RequestHeader("Authorization") String token) {
		String oldPassword = passwordMap.get("oldPassword");
		String newPassword = passwordMap.get("newPassword");
		String confirmPassword = passwordMap.get("confirmPassword");

		Map<String, Object> map = ThreadLocalUtil.get();
		User user = userService.findByUserName(map.get("username").toString());

		if (!PasswordEncodeUtil.encodePassword(oldPassword).equals(user.getPassword())) {
			return ResponseMessage.error(500, "Old password is incorrect");
		}

		if (!newPassword.equals(confirmPassword)) {
			return ResponseMessage.error(500, "New password and confirm password are not the same");
		}

		userService.updatePassword(newPassword);

		// delete token
		redisService.deleteData(token);

		return ResponseMessage.success();
	}
}
