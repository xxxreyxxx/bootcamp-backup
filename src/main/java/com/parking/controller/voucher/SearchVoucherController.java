package com.parking.controller.voucher;

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

import com.parking.entity.voucher.MasterVoucherEntity;
import com.parking.service.voucher.impl.SearchVoucherServiceImpl;

@RestController
public class SearchVoucherController {
	@Autowired
	SearchVoucherServiceImpl searchVoucherServiceImpl;

	private final Logger logger = LoggerFactory.getLogger(SearchVoucherController.class);

	@GetMapping("/search/voucher/code")
	public ResponseEntity<Page<MasterVoucherEntity>> searchByCode(@RequestParam("name") String name,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(
					searchVoucherServiceImpl.searchByVoucherCode(page, pageSize, name, orderBy, sort), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/search/voucher/discount")
	public ResponseEntity<Page<MasterVoucherEntity>> searchByDiscount(@RequestParam("name") String discount,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(
					searchVoucherServiceImpl.searchByDiscount(page, pageSize, discount, orderBy, sort), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/voucher/sort")
	public ResponseEntity<Page<MasterVoucherEntity>> sortAll(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(searchVoucherServiceImpl.sortAll(page, pageSize, orderBy, sort), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
