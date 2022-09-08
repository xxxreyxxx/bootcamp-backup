package com.parking.controller.voucher;

import java.sql.Date;
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

import com.parking.entity.voucher.VoucherTopupEntity;
import com.parking.service.voucher.impl.SearchVoucherTopupServiceImpl;

@RestController
public class SearchVoucherTopupController {
	@Autowired
	SearchVoucherTopupServiceImpl searchVoucherTopupServiceImpl;
	private final Logger logger = LoggerFactory.getLogger(SearchVoucherTopupController.class);

	@GetMapping("/search/topup/code")
	public ResponseEntity<Page<VoucherTopupEntity>> searchByCode(@RequestParam("name") String name,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(
					searchVoucherTopupServiceImpl.searchByVoucherCode(page, pageSize, name, orderBy, sort),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/search/topup/value")
	public ResponseEntity<Page<VoucherTopupEntity>> searchByValue(@RequestParam("name") String value,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(
					searchVoucherTopupServiceImpl.searchByValue(page, pageSize, value, orderBy, sort), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/search/topup/expired")
	public ResponseEntity<Page<VoucherTopupEntity>> searchByExpired(@RequestParam("expired") Date expired,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(
					searchVoucherTopupServiceImpl.searchByExpired(page, pageSize, expired, orderBy, sort),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/topup/sort")
	public ResponseEntity<Page<VoucherTopupEntity>> sortAll(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(searchVoucherTopupServiceImpl.sortAll(page, pageSize, orderBy, sort),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
