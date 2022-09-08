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

import com.parking.entity.user.MasterVehicleEntity;
import com.parking.service.user.impl.SearchVehicleServiceImpl;

@RestController
public class SearchVehicleController {
	@Autowired
	SearchVehicleServiceImpl searchVehicleServiceImpl;
	private final Logger logger = LoggerFactory.getLogger(SearchVehicleController.class);

	@GetMapping("/search/vehicle/plate")
	public ResponseEntity<Page<MasterVehicleEntity>> searchAll(@RequestParam("name") String licensePlate,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(
					searchVehicleServiceImpl.searchByLicensePlate(page, pageSize, licensePlate, orderBy, sort),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/vehicle/sort")
	public ResponseEntity<Page<MasterVehicleEntity>> sortAll(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(searchVehicleServiceImpl.sortAll(page, pageSize, orderBy, sort), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
