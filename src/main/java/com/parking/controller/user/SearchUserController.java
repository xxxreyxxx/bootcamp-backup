
package com.parking.controller.user;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entity.user.MasterUserEntity;
import com.parking.service.user.impl.SearchUserServiceImpl;

@RestController
public class SearchUserController {
	private final Logger logger = LoggerFactory.getLogger(SearchUserController.class);

	@Autowired
	private SearchUserServiceImpl searchUserServiceImpl;

	@GetMapping("/search/user/email")
	public ResponseEntity<Page<MasterUserEntity>> searchByEmail(@RequestParam("name") String email,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(searchUserServiceImpl.searchByEmail(page, pageSize, email, orderBy, sort),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/search/user/fullname")
	public ResponseEntity<Page<MasterUserEntity>> searchByFullname(@RequestParam("name") String fullname,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(searchUserServiceImpl.searchByFullname(page, pageSize, fullname, orderBy, sort),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/user/sort")
	public ResponseEntity<Page<MasterUserEntity>> sortAll(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(searchUserServiceImpl.sortAll(page, pageSize, orderBy, sort), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
