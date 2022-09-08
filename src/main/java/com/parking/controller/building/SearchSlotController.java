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

import com.parking.entity.building.SlotEntity;
import com.parking.service.building.impl.SearchSlotServiceImpl;

@RestController
public class SearchSlotController {
	@Autowired
	SearchSlotServiceImpl searchSlotServiceImpl;

	private final Logger logger = LoggerFactory.getLogger(SearchSlotController.class);

	@GetMapping("/search/slot/{searchBy}")
	public ResponseEntity<Page<SlotEntity>> searchAll(@PathVariable("searchBy") String searchBy,
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

	@GetMapping("/slot/sort")
	public ResponseEntity<Page<SlotEntity>> sortAll(@RequestParam("type") Long slotType,
			@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("orderby") String orderBy, @RequestParam("sort") String sort,
			@RequestParam(name = "admin", required = false) Long adminId) {
		try {
			return new ResponseEntity<>(searchSlotServiceImpl.sortAll(page, pageSize, orderBy, sort, slotType, adminId),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	private ResponseEntity<Page<SlotEntity>> searchBy(String searchBy, String name, Integer page, Integer pageSize,
			String orderBy, String sort, Long slotType, Long adminId) {

		switch (searchBy) {
		case "building":
			return new ResponseEntity<>(searchSlotServiceImpl.searchAllByBuildingName(name, page, pageSize, slotType,
					orderBy, sort, adminId), HttpStatus.OK);
		case "floor":
			return new ResponseEntity<>(
					searchSlotServiceImpl.searchAllByFloorName(name, page, pageSize, orderBy, slotType, sort, adminId),
					HttpStatus.OK);
		case "slot":
			return new ResponseEntity<>(
					searchSlotServiceImpl.searchAllBySlotName(name, page, pageSize, orderBy, sort, slotType, adminId),
					HttpStatus.OK);
		default:
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
