package com.parking.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.user.CheckExist;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.user.MasterUserInfoEntity;
import com.parking.service.user.impl.UserServiceImpl;

@RestController
public class CheckExistController {
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/check")
	public ResponseEntity<CheckExist> checkExist(@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "phonenumber", required = false) String phoneNumber) {
		try {
			return checkBy(email, phoneNumber);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	private ResponseEntity<CheckExist> checkBy(String email, String phoneNumber) {
		if (email != null && phoneNumber != null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else if (email != null) {
			return new ResponseEntity<>(new CheckExist(isEmail(email)), HttpStatus.OK);
		} else if (phoneNumber != null) {
			return new ResponseEntity<>(new CheckExist(isPhoneNumber(phoneNumber)), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CheckExist(false), HttpStatus.OK);
		}
	}

	private boolean isEmail(String email) {
		MasterUserEntity user = userService.findByEmail(email);
		return user != null;
	}

	private boolean isPhoneNumber(String phoneNumber) {
		MasterUserInfoEntity user = userService.findByPhoneNumber(phoneNumber);
		return user != null;
	}
}
