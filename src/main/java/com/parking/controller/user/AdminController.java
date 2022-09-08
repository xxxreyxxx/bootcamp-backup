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

import com.parking.dto.user.AdminDto;
import com.parking.entity.user.MasterUserEntity;
import com.parking.service.admin.impl.AdminServiceImpl;

@RestController
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	AdminServiceImpl adminServiceImpl;

	@PostMapping("/admin")
	public ResponseEntity<MasterUserEntity> signUpUser(@RequestBody AdminDto adminDto) {
		try {
			MasterUserEntity save = adminServiceImpl.createUser(adminDto);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/admin")
	public ResponseEntity<MasterUserEntity> updateUser(@RequestBody AdminDto adminDto) {
		try {
			
			if (adminDto.getPassword() != null) {
				adminDto.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
			}
			
			MasterUserEntity save = adminServiceImpl.updateUser(adminDto);
			save.setPassword(null);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/admin/password")
	public ResponseEntity<MasterUserEntity> updateUserPassword(@RequestBody AdminDto adminDto) {
		try {
			adminDto.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
			MasterUserEntity save = adminServiceImpl.updateUserPassword(adminDto);
			save.setPassword(null);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/admin")
	public ResponseEntity<MasterUserEntity> findById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(adminServiceImpl.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/adminss")
	public ResponseEntity<List<MasterUserEntity>> findAll() {
		try {
			List<MasterUserEntity> user = adminServiceImpl.findAll();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/admins")
	public ResponseEntity<Page<MasterUserEntity>> findAllPaging(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			Page<MasterUserEntity> user = adminServiceImpl.findAll(page, pageSize);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
