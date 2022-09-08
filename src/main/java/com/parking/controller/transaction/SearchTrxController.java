package com.parking.controller.transaction;

import java.sql.Date;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entity.transaction.TransactionDetail;
import com.parking.service.transaction.impl.SearchTrxServiceImpl;

@RestController
public class SearchTrxController {
	@Autowired
	SearchTrxServiceImpl searchServiceImpl;

	private final Logger logger = LoggerFactory.getLogger(SearchTrxController.class);

	@GetMapping("/search/{searchBy}")
	public ResponseEntity<Page<TransactionDetail>> searchAll(@PathVariable("searchBy") String searchBy,
			@RequestParam("name") String name, @RequestParam("type") Long type, @RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort, @RequestParam(name = "admin", required = false) Long adminId) {
		try {
			return searchBy(searchBy, name, page, pageSize, orderBy, sort, type, adminId);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/search/date")
	public ResponseEntity<Page<TransactionDetail>> searchByBetweenDate(@RequestParam("start") Date startDate,
			@RequestParam("end") Date endDate, @RequestParam("type") Long type, @RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort, @RequestParam(name = "admin", required = false) Long adminId) {
		try {
			return new ResponseEntity<>(searchServiceImpl.searchAllByBeteenDate(startDate, endDate, page, pageSize,
					orderBy, sort, type, adminId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/sort")
	public ResponseEntity<Page<TransactionDetail>> sortAllTrx(@RequestParam("type") Long type,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort,
			@RequestParam(name = "admin", required = false) Long adminId) {
		try {
			return new ResponseEntity<>(searchServiceImpl.sortAllTrx(page, pageSize, orderBy, sort, type, adminId),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	private ResponseEntity<Page<TransactionDetail>> searchBy(String searchBy, String name, Integer page,
			Integer pageSize, String orderBy, String sort, Long slotType, Long adminId) {

		switch (searchBy) {
		case "building":
			return new ResponseEntity<>(
					searchServiceImpl.searchAllByBuildingName(name, page, pageSize, orderBy, sort, slotType, adminId),
					HttpStatus.OK);
		case "plate":
			return new ResponseEntity<>(
					searchServiceImpl.searchAllByLisencePlate(name, page, pageSize, orderBy, sort, slotType, adminId),
					HttpStatus.OK);
		default:
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
