package com.parking.controller.building;

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

import com.parking.entity.building.MasterBuildingEntity;
import com.parking.service.building.impl.SearchBuildingServiceImpl;

@RestController
public class SearchBuildingController {
	@Autowired
	SearchBuildingServiceImpl searchBuildingServiceImpl;

	private final Logger logger = LoggerFactory.getLogger(SearchBuildingController.class);

	@GetMapping("/search/building/{searchBy}")
	public ResponseEntity<Page<MasterBuildingEntity>> searchByAll(@PathVariable("searchBy") String searchBy,
			@RequestParam("name") String name, @RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort) {
		try {
			return searchBy(searchBy, name, page, pageSize, orderBy, sort);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/building/sort")
	public ResponseEntity<Page<MasterBuildingEntity>> sortAll(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort) {
		try {
			return new ResponseEntity<>(searchBuildingServiceImpl.sortAll(page, pageSize, orderBy, sort),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	private ResponseEntity<Page<MasterBuildingEntity>> searchBy(String searchBy, String name, Integer page,
			Integer pageSize, String orderBy, String sort) {

		switch (searchBy) {
		case "building":
			return new ResponseEntity<>(
					searchBuildingServiceImpl.searchAllByBuildingName(name, page, pageSize, orderBy, sort),
					HttpStatus.OK);
		case "location":
			return new ResponseEntity<>(
					searchBuildingServiceImpl.searchAllByLocation(name, page, pageSize, orderBy, sort), HttpStatus.OK);
		default:
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
