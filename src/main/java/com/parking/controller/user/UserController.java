package com.parking.controller.user;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.user.UserDto;
import com.parking.entity.user.MasterUserEntity;
import com.parking.service.user.impl.UserServiceImpl;

@RestController
public class UserController {
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/auth/registration")
	public ResponseEntity<MasterUserEntity> signUpUser(@RequestBody UserDto user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			MasterUserEntity save = userServiceImpl.saveUser(user);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/user")
	public ResponseEntity<MasterUserEntity> updateUser(@RequestBody UserDto user) {
		try {
			MasterUserEntity save = userServiceImpl.updateUser(user);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/user/password")
	public ResponseEntity<MasterUserEntity> updateUserPassword(@RequestBody UserDto user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			MasterUserEntity save = userServiceImpl.updateUserPassword(user);
			save.setPassword(null);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/user")
	public ResponseEntity<MasterUserEntity> findById(@RequestParam Long id) {
		try {
			MasterUserEntity user = userServiceImpl.findById(id);
			user.setPassword(null);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<MasterUserEntity>> findAll() {
		try {
			List<MasterUserEntity> user = userServiceImpl.findAll();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/users/pages")
	public ResponseEntity<Page<MasterUserEntity>> findAllPaging(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			Page<MasterUserEntity> user = userServiceImpl.findAll(page, pageSize);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
