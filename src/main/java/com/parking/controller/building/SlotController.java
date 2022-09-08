package com.parking.controller.building;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.building.SlotDto;
import com.parking.entity.building.SlotEntity;
import com.parking.service.building.impl.SlotServiceImpl;

@RestController
public class SlotController {
	@Autowired
	SlotServiceImpl slotServiceImpl;
	
	private final Logger logger = LoggerFactory.getLogger(SlotController.class);

	@PostMapping("/slot")
	public ResponseEntity<SlotEntity> createSlot(@RequestBody SlotDto slot) {
		try {
			return new ResponseEntity<>(slotServiceImpl.saveSlot(slot), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/slot")
	public ResponseEntity<SlotEntity> updateSlot(@RequestBody SlotDto slot) {
		try {
			return new ResponseEntity<>(slotServiceImpl.updateSlot(slot), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/slot")
	public ResponseEntity<SlotEntity> findById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(slotServiceImpl.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/slots")
	public ResponseEntity<Page<SlotEntity>> findAllSlot(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			return new ResponseEntity<>(slotServiceImpl.findAll(page, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/slots/admin")
	public ResponseEntity<Page<SlotEntity>> findAllSlotBySlotTypeAndAdminId(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("type") Long slotType, @RequestParam("id") Long adminId) {
		try {
			return new ResponseEntity<>(slotServiceImpl.findAllByTypeSlotAndAdminId(page, pageSize, slotType, adminId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/slots/pages")
	public ResponseEntity<Page<SlotEntity>> findAllSlotBySlotType(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("type") Long slotType) {
		try {
			return new ResponseEntity<>(slotServiceImpl.findAllByTypeSlot(page, pageSize, slotType), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("/slot")
	public ResponseEntity<SlotEntity> deleteSlot(@RequestParam Long id) {
		try {
			return new ResponseEntity<>(slotServiceImpl.deleteBuilding(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(Arrays.toString(e.getStackTrace()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
