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

import com.parking.entity.building.MasterFloorEntity;
import com.parking.service.building.impl.SearchFloorServiceImpl;

@RestController
public class SearchFloorController {
	@Autowired
	SearchFloorServiceImpl searchFloorServiceImpl;

	private final Logger logger = LoggerFactory.getLogger(SearchFloorController.class);

	@GetMapping("/search/floor/{searchBy}")
	public ResponseEntity<Page<MasterFloorEntity>> searchAll(@PathVariable("searchBy") String searchBy,
			@RequestParam("name") String name, @RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort, @RequestParam(name = "admin", required = false) Long adminId) {
		try {
			return searchBy(searchBy, name, page, pageSize, orderBy, sort, adminId);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/floor/sort")
	public ResponseEntity<Page<MasterFloorEntity>> sortAll(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderby") String orderBy,
			@RequestParam("sort") String sort, @RequestParam(name = "admin", required = false) Long adminId) {
		try {
			return new ResponseEntity<>(searchFloorServiceImpl.sortAll(page, pageSize, orderBy, sort, adminId),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	private ResponseEntity<Page<MasterFloorEntity>> searchBy(String searchBy, String name, Integer page,
			Integer pageSize, String orderBy, String sort, Long adminId) {

		switch (searchBy) {
		case "building":
			return new ResponseEntity<>(
					searchFloorServiceImpl.searchAllByBuildingName(name, page, pageSize, orderBy, sort, adminId),
					HttpStatus.OK);
		case "floor":
			return new ResponseEntity<>(
					searchFloorServiceImpl.searchAllByFloorName(name, page, pageSize, orderBy, sort, adminId),
					HttpStatus.OK);
		default:
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
